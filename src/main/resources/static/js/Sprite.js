class Sprite {
    constructor(config) { //Pass in details of the animations of a sprite's sheet * next line will have keys of the names of the naimations with a series of frames * array of frames and each array will have a pair

        //Set up the image
        this.image = new Image(); //creates new image in memory
        this.image.src = config.src; // downloads right image
        this.image.onload = () => {
            this.isLoaded = true; // flag that tells if image was loaded
        }

        //Shadow
        this.shadow = new Image();
        this.useShadow = true; //config.useShadow || false
        if (this.useShadow) {
            this.shadow.src = "images/shadow.png";
        }
        this.shadow.onload = () => {
            this.isShadowLoaded = true;
        }

        //Configuration of Animation and Initial State of Sprite
        this.animations = config.animations || {
           idleDown: [
               [0,0]
           ]
        }
        this.currentAnimation = config.currentAnimation || "idleDown"; // captures actual animation and frame of animation
        this.currentAnimationFrame = 0; // which animation frame should be showing

        //Reference the game object
        this.gameObject = config.gameObject; // whenever run new sprite, responsible for passing in a gameObject to configuration
    }
    //takes in context to where to draw to
    draw(ctx) {
        const x = this.gameObject.x  - 8; // x and y nudges position of game object on screen
        const y = this.gameObject.y  - 18;

        this.isShadowLoaded && ctx.drawImage(this.shadow, x, y) // shadow.png is a 32x32 square instead of a sprite sheet

        //If flag true, start drawing image
        this.isLoaded && ctx.drawImage(this.image,
            0,0, //left cut and right cut
            32,32, //size of cut *maybe pass in configuration here cuz not all sprites are cut the same, some are cut in 8x8 sqaures or 10x2 rectangales
            x,y, //position of spirte should be drawn to canvas *note* make em levitate by adding -16
            32,32 //size in which sprite should be drawn
        )
    }
}