class OverworldEvent {
    constructor({ map, event}) {
        this.map = map;
        this.event = event;
    }

    stand(resolve) {
        const who = this.map.gameObjects[ this.event.who ]; // pulls referebce from game object // Npc1, etc
        who.startBehavior({
            map: this.map
        }, {
            type: "stand",
            direction: this.event.direction,
            time: this.event.time
        })

        //Set up a handler to complete when the correct person is done walking, then resolve the event
        const completeHandler = e => {
            if (e.detail.whoId === this.event.who) {
                document.removeEventListener("PersonStandComplete", completeHandler);
                resolve();
            }
        }
        document.addEventListener("PersonStandComplete", completeHandler)
    }

    walk(resolve) {
        const who = this.map.gameObjects[ this.event.who ]; // pulls referebce from game object // Npc1, etc
        who.startBehavior({
            map: this.map
        }, {
            type: "walk",
            direction: this.event.direction,
            retry: true
        })

        //Set up a handler to complete when the correct person is done walking, then resolve the event
        const completeHandler = e => {
            if (e.detail.whoId === this.event.who) {
                document.removeEventListener("PersonWalkingComplete", completeHandler);
                resolve();
            }
        }
        document.addEventListener("PersonWalkingComplete", completeHandler)

    }

    //Kicks off instructional methods ^
    init() {
        return new Promise(resolve => {
            this[this.event.type](resolve)
        })
    }

}