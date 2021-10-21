class Overworld //top level parent component
{ 
    constructor(config) 
    {
        this.element = config.element; //pass in element for overworld to operate on
        this.canvas = this.element.querySelector(".game-canvas"); // grabs canvas tag from style.css
        this.ctx = this.canvas.getContext("2d"); // drawing from the context, gives access to alot of drawing methods that exists in canvas element
    }

    init() 
    {
        const image = new Image(); //create new image, assign a source to that image, copy over pixels to the canvas, ctx (context) allows for drawing on canvas
        image.onload = () => {
            this.ctx.drawImage(image)//wants a few arguments, simple: pass in the image we want to draw pixels from and then an x,ycoordinate where we wil draw image from
        };
        image.src = "images/DemoLower.png";


        //layering effects, character goes after background
        //counting by 16x16 cells top left corner of map; characters 32x32

        const x = 5;
        const y = 6;

        const shadow = new Image();
        shadow.onload = () => {
            this.ctx.drawImage(
                shadow, 
                0, //left cut
                0, //top cut,
                32, //width of cut
                32, //height of cut
                x * 16 - 8, //actual position of sprite
                y * 16 - 18, // actual position of sprite
                32, // natural cut
                32      
        )
        }
        shadow.src = "images/shadow.png";


        const hero = new Image();
        hero.onload = () => {
            //Draw the hero...
            this.ctx.drawImage(
                hero, 
                0, //left cut
                0, //top cut,
                32, //width of cut
                32, //height of cut
                x * 16 - 8, //actual position of sprite
                y * 16 - 18, // actual position of sprite
                32, // natural cut
                32      
        )
        }
        hero.src = "images/Alchemist_idle.png"


    }

}