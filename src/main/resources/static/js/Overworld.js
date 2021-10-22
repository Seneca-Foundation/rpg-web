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
            this.ctx.drawImage(image, 0, 0)//wants a few arguments, simple: pass in the image we want to draw pixels from and then an x,ycoordinate where we wil draw image from
        };
        image.src = "images/DemoLower.png";


        //layering effects, character goes after background
        //counting by 16x16 cells top left corner of map; characters 32x32

        //Place some Game Objects!
        const hero = new GameObject({
            x: 5,
            y: 6,
            src: "images/test.png"
        })
        const npc1 = new GameObject({
            x: 7,
            y: 9,
            src: "images/shadowelf.png"
        })


        setTimeout(() => {
            hero.sprite.draw(this.ctx);
            npc1.sprite.draw(this.ctx);
        }, 200)
        


    }

}