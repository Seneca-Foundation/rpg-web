class DirectionInput {
    constructor() {
        this.heldDirections = []; //keep track of arrow presses in an array

        this.map = {
            "ArrowUp": "up",
            "ArrowDown": "down",
            "ArrowLeft": "left",
            "ArrowRight": "right",
            "KeyW": "up",
            "KeyS": "down",
            "KeyA": "left",
            "KeyD": "right",
        }
    }

    get direction() { //clean way to retrieve external info of directions
        return this.heldDirections[0];
    }

    init() {
        document.addEventListener("keydown", e => { // listens for key down events
            console.log(e.code);
            const dir = this.map[e.code];
            if (dir && this.heldDirections.indexOf(dir) === -1){
                this.heldDirections.unshift(dir);
                console.log(this.heldDirections)
            }
        });
        document.addEventListener("keyup", e => {
            const dir = this.map[e.code];
            const index = this.heldDirections.indexOf(dir);
            if (index > -1) {
                this.heldDirections.splice(index, 1);
                console.log(this.heldDirections)
            }
        })
    }

}