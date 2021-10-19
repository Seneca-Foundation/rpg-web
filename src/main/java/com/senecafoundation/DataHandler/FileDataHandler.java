package com.senecafoundation.DataHandler;

import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import com.senecafoundation.CharacterTypes.Character;
import com.senecafoundation.CharacterTypes.ICharacter;
import com.senecafoundation.CharacterTypes.Orc;

public class FileDataHandler extends CharacterDataHandler {
    
    private String fileLocation;
    private File file;
    Scanner scanner;
    
    public FileDataHandler(String fileLocationFromUser) {
        this.fileLocation = fileLocationFromUser;
        try {
            this.file = new File(fileLocationFromUser);
            this.scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean Create(ICharacter characterFromUser) 
    {
        try {
            // See if it exists in the file already
            this.Read(characterFromUser.getId());
        }
        catch (Exception e) { // We catch the custom error here (from line 60)
            if (e.getMessage() == "Item not found with that ID" || e instanceof FileNotFoundException) {
                // Was not found in the file - add it
                BufferedWriter bw;
                try {
                    bw = new BufferedWriter(new FileWriter(this.fileLocation, true));
                    bw.write(characterFromUser.toString());
                    bw.newLine();
                    bw.flush();
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return true;
        
    }

    @Override
    public Character Read(UUID id) throws Exception 
    {      
        if (this.file != null) {
            try {
                this.scanner = new Scanner(this.file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (this.scanner != null && this.scanner.hasNextLine()) {
                String line = this.scanner.nextLine();
                if(line.contains(id.toString())) { 
                    String[] props = line.split(",");
                    if (props[0].equals("Orc")) {
                        Orc orcToReturn = new Orc(props[2], Integer.parseInt(props[3]), props[4], Integer.parseInt(props[5]), Integer.parseInt(props[6]));
                        orcToReturn.setId(id);
                        return orcToReturn;
                    }
                }
            }
        }
        // We throw a custom error here if we can't find anything with that ID
        throw new Exception("Item not found with that ID");
    }

    @Override
    public ICharacter Update(ICharacter characterToUpdate) 
    {

        try {
            this.Delete(characterToUpdate.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.Create(characterToUpdate);
        return characterToUpdate;
    }

    @Override
    public boolean Delete(UUID id) throws Exception
    {
        // This is delete from a file
        //now read the file line by line...
        ArrayList<String> lines = new ArrayList<String>();
        if (this.file != null) 
        {
            this.scanner = new Scanner(this.file);
            while (this.scanner != null && this.scanner.hasNextLine()) 
            {
                String line = this.scanner.nextLine();
                if(!line.contains(id.toString())) 
                { 
                    lines.add(line);
                }
            }
        }

        BufferedWriter bw;
        try 
        {
            bw = new BufferedWriter(new FileWriter(this.fileLocation));
            lines.forEach(lineToWrite ->
                { 
                    try 
                    {
                        bw.write(lineToWrite);
                        bw.newLine();
                    } catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                }
            );
            bw.flush();
            bw.close();
            return true;
        } catch (IOException e1) 
        {
            e1.printStackTrace();
        }
        // We throw a custom error here if we can't find anything with that ID
        throw new Exception("Item not found with that ID");
    }

    @Override
    public List<ICharacter> ReadAll() {
        // TODO Auto-generated method stub
        return null;
    }
}