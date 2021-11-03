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

    bindActionInput() {
        new KeyPressListener("Enter", () => {
          //Is there a person here to talk to?
          this.map.checkForActionCutscene()
        })
      }

      bindHeroPositionCheck() {
        document.addEventListener("PersonWalkingComplete", e => {
          if (e.detail.whoId === "hero") {
            //Hero's position has changed
            this.map.checkForFootstepCutscene()
          }
        })
      }

      startMap(mapConfig) {
        this.map = new OverworldMap(mapConfig);
        this.map.overworld = this;
        this.map.mountObjects();
       }

    init() 
    {
        this.startMap(window.OverworldMaps.DemoRoom);


        this.bindActionInput();
        this.bindHeroPositionCheck();

        this.directionInput = new DirectionInput();
        this.directionInput.init();               //get key bindings on document
        //this.directionInput.direction; //"down","up",etc

        this.startGameLoop();
        this.bindHeroPositionCheck();


        //change to fit cutscene of scenario
        // this.map.startCutscene([
        //     {who: "hero", type: "walk", direction: "up" }, //Hero Scene
        //     {who: "hero", type: "walk", direction: "up" },
        //     {who: "hero", type: "walk", direction: "up" },
        //     {who: "hero", type: "stand", direction: "up"},

        //     {who: "npc6B", type: "walk", direction: "up" }, //Exit Door Mercenary
        //     {who: "npc6B", type: "stand", direction: "up"},

        //     {who: "npc6", type: "walk", direction: "down" }, //Mercenary Receiver
        //     {who: "npc6", type: "walk", direction: "down" },
        //     {who: "npc6", type: "walk", direction: "left" },
        //     {who: "npc6", type: "walk", direction: "down" },
        //     {who: "npc6", type: "walk", direction: "down" },
        //     {who: "npc6", type: "stand", direction: "down", time: 800 },

        //     {who: "npc1", type: "stand", direction: "right", time: 800 }, //Nosy Pandas
        //     {who: "npc2", type: "stand", direction: "right", time: 1000 },

        //     {who: "npc3", type: "stand", direction: "up", time: 800 }, //Shadowlord and Henchmen 
        //     {who: "npc4", type: "stand", direction: "up", time: 800 },

        //     {who: "npc7", type: "stand", direction: "left", time: 800 }, //Female Bartender
            
        //     {who: "npc10", type: "stand", direction: "right", time: 800 }, //Doctor
        //     {type: "textMessage", text: "WHY HELLO THERE!"}

        // ])
    }
}