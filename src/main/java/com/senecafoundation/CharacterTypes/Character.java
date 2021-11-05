package com.senecafoundation.CharacterTypes;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


import com.senecafoundation.DataHandler.Interfaces.IDataHandler;

@MappedSuperclass
public abstract class Character extends BaseStats implements ICharacter 
{
    //Variables
    private String name;
    private int age;
    private String sex;
    private String sprite_url;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    public String getSprite_url() {
        return sprite_url;
    }
    public void setSprite_url(String sprite_url) {
        this.sprite_url = sprite_url;
    }
    public String getName()
    {
        return name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }

    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
  
    //Constructor
    public Character(String name, int age, String sex, int health, int mana, int stamina)
    {
        super(health, mana, stamina);
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.setId(UUID.randomUUID());
    }

    public Character() {
        super();
    }
    //Methods
    public String PlayerDetails() //virtual
    {
        return "Player Name: " + name +  " Player Age: " + age + " Sex: " + sex;
    }

    public String toString() 
    {
        return this.getClass().getSimpleName() + "," + this.getId().toString() + "," + this.getName() + ","  + this.getAge() + "," + this.getSex();
    }  

}