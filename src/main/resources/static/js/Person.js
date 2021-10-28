class Person extends GameObject {
    
    // Audio Sounds
    pokemonWallBump = new Audio("music/pokemon-wall-bump.mp3");
    // rpgMusicBump = new Audio("music/rpgMusicLoop.mp3");

    constructor(config) {
        super(config)
        this.movingProgressRemaining = 0; //moves by cell blocks, this far away and keep moving until it reaches the point

        this.isPlayerControlled = config.isPlayerControlled || false;

        this.directionUpdate = {
            "up": ["y", -1],
            "down": ["y", 1],
            "left": ["x", -1],
            "right": ["x", 1],
        }
    }
    // extended from GameObject
    update(state) {
        if (this.movingProgressRemaining > 0){
            this.updatePosition();
        }
        else {

            //More case for starting to walk will come here
            //
            //

            //Case: We're keyboard ready and have an arrow pressed 
            if (this.isPlayerControlled && state.arrow) { //detecting when an arrow is coming in and validating character's movement
                this.startBehavior(state, {
                    type: "walk",
                    direction: state.arrow
                })
            }
            this.updateSprite(state);
        }
    }

    startBehavior(state, behavior) { //explicitly fire a walk command from a person w/out coming form arrow keys
        //Set character direction to whatever behavior it has
        this.direction = behavior.direction;
        if (behavior.type === "walk") {

            //Stop here if space is not free
            if (state.map.isSpaceTaken(this.x, this.y, this.direction)) {
                this.pokemonWallBump.play();
                return;
            }

            //Ready to Walk
            state.map.moveWall(this.x, this.y, this.direction);
            this.movingProgressRemaining = 16;
        }
    }

    updatePosition() {
         //uppdating x and y value
            const [property, change] = this.directionUpdate[this.direction] // array to grab values, value we one to change of directionUpdate property either x or y | change 1 or -1
            this[property] += change;
            this.movingProgressRemaining -= 1;
    }

    updateSprite() {

        if (this.movingProgressRemaining > 0) {
            this.sprite.setAnimation("walk-"+this.direction);
            return ;
        }
        this.sprite.setAnimation("idle-"+this.direction);//for every frame, call setAnimation method
    }
}