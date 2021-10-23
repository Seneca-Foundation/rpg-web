class Overworld //top level parent component
{ 
    constructor(config) 
    {
        this.element = config.element; //pass in element for overworld to operate on
        this.canvas = this.element.querySelector(".game-canvas"); // grabs canvas tag from style.css
        this.ctx = this.canvas.getContext("2d"); // drawing from the context, gives access to alot of drawing methods that exists in canvas element
        this.map = null;
    }

    startGameLoop() {
        const step = () => { //actual funtion that calls every frame

            //Clears off the canvas
            this.ctx.clearRect(0,0, this.canvas.width, this.canvas.height);

            //Draw Lower Layer
            this.map.drawLowerImage(this.ctx);

            //Draw Game Objects  *Note* game obejct stored as an object of game objects;  Object.values ---> take values of key value store and iterate through them
            Object.values(this.map.gameObjects).forEach(object => {
                object.update({
                    arrow: this.directionInput.direction
                })
                object.sprite.draw(this.ctx);
            })

            //Draw Upper Layer
            this.map.drawUpperImage(this.ctx);


            requestAnimationFrame(() => { // calls funtions whever new frame begins
            step();
            })
        }
        step();
    }

    init() 
    {
        this.map = new OverworldMap(window.OverworldMaps.DemoRoom);

        this.directionInput = new DirectionInput();
        this.directionInput.init();               //get key bindings on document
        this.directionInput.direction; //"down","up",etc

        this.startGameLoop();
    }
}