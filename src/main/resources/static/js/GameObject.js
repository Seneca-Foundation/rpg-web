class GameObject {
    constructor(config) {       //constructor to gather all necessary data for a game object
        this.id = null;
        this.isMounted = false;
        this.x = config.x || 0; //Every game object will have a position on the map            access to properties ->gameObject: this **sprite line is where you give all the attributes of a sprite**
        this.y = config.y || 0;
        this.direction = config.direction || "down";
        this.sprite = new Sprite({
            gameObject: this,
            src: config.src || "images/characters/hero.png",
        });     // attributes of a character/sprite

        this.behaviorLoop = config.behaviorLoop || []; // what one passes in, the values
        this.behaviorLoopIndex = 0; // keeps track of which behavior we are on

    }

    mount(map) {
        console.log("mounting!")
        this.isMounted = true;
        map.addWall(this.x, this.y); //adds sprite to scene

        // If we have a behavior, kick off after a short delay
        setTimeout(() => {
            this.doBehaviorEvent(map);
        }, 10)
    }

    update(){
    }

    async doBehaviorEvent(map) {

        //Don't do anything if there is a more important cutscene or I don't have config to do anything anyway
        if (map.isCutscenePlaying || this.behaviorLoop.length === 0 || this.isStanding) {
            return;
        }
        
        //Setting up our event with relevant info
        let eventConfig = this.behaviorLoop[this.behaviorLoopIndex]; // pulls out event of the index one is on
        eventConfig.who = this.id; //npc/sprite that does it

        //Create an event instance out of out next event config
        const eventHandler = new OverworldEvent({ map, event: eventConfig}); // takes in configuration; gonna take in code that instructs ppl what to do; music changes, battle starting text messages popping up
        await eventHandler.init(); // wait until this event is finished then move on to the next event. In other words, returning a promise, when that contract is resolved continue with the loop

        //Setting the next event to fire
        this.behaviorLoopIndex += 1;
        if (this.behaviorLoopIndex === this.behaviorLoop.length) { //resolves loop
            this.behaviorLoopIndex = 0;
        }

        //Do it again!
        this.doBehaviorEvent(map);

    }

}