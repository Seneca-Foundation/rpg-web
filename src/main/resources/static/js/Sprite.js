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
           "idle-down" : [ [0,0] ],
           "idle-right": [ [0,1] ],
           "idle-up"   : [ [0,2] ],
           "idle-left" : [ [0,3] ],
           "walk-down" : [ [1,0], [0,0], [3,0], [0,0], ],
           "walk-right": [ [1,1], [0,1], [3,1], [0,1], ],
           "walk-up"   : [ [1,2], [0,2], [3,2], [0,2], ],
           "walk-left" : [ [1,3], [0,3], [3,3], [0,3], ]
        }
        // captures actual animation and frame of animation
        this.currentAnimation = "idle-right";//config.currentAnimation || "idle-down"; 
        this.currentAnimationFrame = 0; // which animation frame should be showing

        this.animationFrameLimit = config.animationFrameLimit || 8; //how many game loop frames to show in one cut of the sprite, increase number, the spirte will move slower ; cadence
        this.animationFrameProgress = this.animationFrameLimit; // how much time is left until to switch to other frame ; starts high then goes to downward

        //Reference the game object
        this.gameObject = config.gameObject; // whenever run new sprite, responsible for passing in a gameObject to configuration
    }

    //figure out which animation we're on and which animation frame we're on
    get frame() {
        return this.animations[this.currentAnimation][this.currentAnimationFrame];
    }
    //changes animation when key is pressed
    setAnimation(key) {
        if (this.currentAnimation !== key) {
            this.currentAnimation = key;
            this.currentAnimationFrame = 0;
            this.animationFrameProgress = this.animationFrameLimit;
        }
    }

    //call this for each sprite image being drawn
    updateAnimationProgress() {
        //Downtick frame progress
        if (this.animationFrameProgress > 0) {
            this.animationFrameProgress -= 1;
            return;
        }

        //Reset the Counter
        this.animationFrameProgress = this.animationFrameLimit;
        this.currentAnimationFrame += 1; // increase current animation pic

        if (this.frame === undefined) {
            this.currentAnimationFrame = 0;
        }
        
    }

    //takes in context to where to draw to
    draw(ctx, cameraPerson) {
        const x = this.gameObject.x  - 8 + utils.withGrid(10.5) - cameraPerson.x; // x and y nudges position of game object on screen
        const y = this.gameObject.y  - 18 + utils.withGrid(6) - cameraPerson.y;

        this.isShadowLoaded && ctx.drawImage(this.shadow, x, y) // shadow.png is a 32x32 square instead of a sprite sheet

        //gives back an array of coordinates
        const [frameX, frameY] = this.frame;

        //If flag true, start drawing image
        this.isLoaded && ctx.drawImage(this.image,
            frameX * 32, frameY * 32, //left cut and right cut *Note 32 is grid size of the sprite sheet design*
            32,32, //size of cut *maybe pass in configuration here cuz not all sprites are cut the same, some are cut in 8x8 sqaures or 10x2 rectangales
            x,y, //position of spirte should be drawn to canvas *note* make em levitate by adding -16
            32,32 //size in which sprite should be drawn
        )

        this.updateAnimationProgress();
    }

}