class Person extends GameObject {
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
        this.updatePosition();

        if (this.isPlayerControlled && this.movingProgressRemaining === 0 && state.arrow) { //detecting when an arrow is coming in and validating character's movement
            this.direction = state.arrow;
            this.movingProgressRemaining = 16;
        }
    }

    updatePosition() {
        if (this.movingProgressRemaining > 0){ //uppdating x and y value
            const [property, change] = this.directionUpdate[this.direction] // array to grab values, value we one to change of directionUpdate property either x or y | change 1 or -1
            this[property] += change;
            this.movingProgressRemaining -= 1;
        }
    }

}