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

            //Establish the camera person
            const cameraPerson = this.map.gameObjects.hero;

            //Update All Objects
            Object.values(this.map.gameObjects).forEach(object => {
                object.update({
                    arrow: this.directionInput.direction,
                    map: this.map,
                })
            })

            //Draw Lower Layer
            this.map.drawLowerImage(this.ctx, cameraPerson);

            //Draw Game Objects  *Note* game obejct stored as an object of game objects;  Object.values ---> take values of key value store and iterate through them
            Object.values(this.map.gameObjects).sort((a,b) => {
                return a.y - b.y; // the lower y values will be upfront of the array the greater y value will be at the end of the array. Northern characters will drawn before the southern characters
            }).forEach(object => {
                object.sprite.draw(this.ctx, cameraPerson);
            })

            //Draw Upper Layer
            this.map.drawUpperImage(this.ctx, cameraPerson);


            requestAnimationFrame(() => { // calls funtions whever new frame begins
            step();
            })
        }
        step();
    }

    init() 
    {
        this.map = new OverworldMap(window.OverworldMaps.DemoRoom);
        this.map.mountObjects();

        this.directionInput = new DirectionInput();
        this.directionInput.init();               //get key bindings on document
        //this.directionInput.direction; //"down","up",etc

        this.startGameLoop();


        //change to fit cutscene of scenario
        this.map.startCutscene([
            {who: "hero", type: "walk", direction: "down" },
            {who: "hero", type: "walk", direction: "down" },
            {who: "hero", type: "walk", direction: "down" },
            {who: "hero", type: "walk", direction: "down" },
            {who: "npc6", type: "walk", direction: "down" },
            {who: "npc6", type: "stand", direction: "up", time: 800 },
        ])
    }
}