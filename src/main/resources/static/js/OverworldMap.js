class OverworldMap {
    constructor(config) {
        this.gameObjects = config.gameObjects;
        this.walls = config.walls || {}; // keeps track of wall objects in game

        this.lowerImage = new Image();
        this.lowerImage.src = config.lowerSrc; // lower layer-contains tiles floor where character will stand on

        this.upperImage = new Image();
        this.upperImage.src = config.upperSrc; // upper layers- rooftops and etc

        this.isCutscenePlaying = true;
    }

    drawLowerImage(ctx, cameraPerson) { //draws lower images
        ctx.drawImage(
            this.lowerImage, 
            utils.withGrid(10.5) - cameraPerson.x, 
            utils.withGrid(6) - cameraPerson.y
            ) 
    }

    drawUpperImage(ctx, cameraPerson) { // draws upper images
        ctx.drawImage(
            this.upperImage, 
            utils.withGrid(10.5) - cameraPerson.x, 
            utils.withGrid(6) - cameraPerson.y
            ) 
    }

    isSpaceTaken(currentX, currentY, direction) {
        const {x,y} = utils.nextPosition(currentX, currentY, direction);
        return this.walls[`${x},${y}`] || false;
    }

    mountObjects() {
        Object.keys(this.gameObjects).forEach(key =>{ //iterates through all the keys of each object to pipe in as the id

            let object = this.gameObjects[key];
            object.id = key; // "hero, NpcA, etc"

            //TODO: determine if this object should actually mount
            object.mount(this);
        })
    }

    async startCutscene(events) {
        this.isCutscenePlaying = true;

        for (let i=0; i<events.length; i++) {
            const eventHandler = new OverworldEvent({
                event: events[i],
                map: this,
            })
            await eventHandler.init();
        }

        this.isCutscenePlaying = false;
    }

    //Adds wall to player object, then removes it when it moves and adds another and so on
    addWall(x,y) {
        this.walls[`${x},${y}`] = true;
    }
    removeWall(x,y) {
        delete this.walls[`${x},${y}`]
    }
    moveWall(wasX, wasY, direction) {
        this.removeWall(wasX, wasY);
        const {x,y} = utils.nextPosition(wasX, wasY, direction);
        this.addWall(x,y);
    }
}
    window.OverworldMaps = {
        DemoRoom: {
            lowerSrc: "images/places/Tavern.png",
            upperSrc: "images/places/TavernUpper.png",
            gameObjects: {
                hero: new Person({
                    isPlayerControlled: true,
                    x: utils.withGrid(5),
                    y: utils.withGrid(6),
                     src: "images/characters/shadowelf.png"
                }),
                npc1: new Person({
                    x: utils.withGrid(2),
                    y: utils.withGrid(7),
                    src: "images/characters/panda.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left", time: 800},
                        {type: "stand", direction: "up", time: 800},
                        {type: "stand", direction: "right", time: 1200},
                        {type: "stand", direction: "up", time: 300},
                    ]
                }),
                npc2: new Person({
                    x: utils.withGrid(4),
                    y: utils.withGrid(7),
                    src: "images/characters/panda.png",
                    behaviorLoop: [
                        {type: "walk", direction: "left"},
                        {type: "stand", direction: "up", time: 800},
                        {type: "walk", direction: "up"},
                        {type: "walk", direction: "right"},
                        {type: "walk", direction: "down"},
                    ]
                }),
                npc3: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(10),
                    src: "images/characters/shadowLord.png"
                }),
                npc4: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(10),
                    src: "images/characters/shadowLordHenchmen.png"
                }),
                npc5: new Person({
                    x: utils.withGrid(3),
                    y: utils.withGrid(4),
                    src: "images/characters/bartenderBeastman.png"
                }),
                npc6: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(4),
                    src: "images/characters/mercenaryBeastman.png"
                }),
                npc7: new Person({
                    x: utils.withGrid(12),
                    y: utils.withGrid(6),
                    src: "images/characters/femaleBartenderBeastman.png"
                }),
                npc8: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(7),
                    src: "images/characters/royaltyBeastman.png"
                }),
                npc9: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(7),
                    src: "images/characters/angel.png"
                }),
                npc10: new Person({
                    x: utils.withGrid(2),
                    y: utils.withGrid(10),
                    src: "images/characters/doctorBeastmen.png"
                }),
                musicPlayer: new Person({
                    x: utils.withGrid(12),
                    y: utils.withGrid(11),
                    src: "images/characters/musicBeastman.png"
                })
            },
            walls: {
                // "16,16" : true
                [utils.asGridCoord(0,0)] : true, // outer perimeter top
                [utils.asGridCoord(1,0)] : true,
                [utils.asGridCoord(2,0)] : true,
                [utils.asGridCoord(3,0)] : true,
                [utils.asGridCoord(4,0)] : true,
                [utils.asGridCoord(5,0)] : true, 
                [utils.asGridCoord(6,0)] : true,
                [utils.asGridCoord(7,0)] : true,
                [utils.asGridCoord(8,0)] : true,
                [utils.asGridCoord(9,0)] : true,
                [utils.asGridCoord(10,0)] : true, 
                [utils.asGridCoord(11,0)] : true,
                [utils.asGridCoord(12,0)] : true,

                [utils.asGridCoord(13,1)] : true, // outer right perimeter
                [utils.asGridCoord(13,2)] : true,
                [utils.asGridCoord(13,3)] : true,
                [utils.asGridCoord(13,4)] : true, 
                [utils.asGridCoord(13,5)] : true,
                [utils.asGridCoord(13,6)] : true,
                [utils.asGridCoord(13,7)] : true,
                [utils.asGridCoord(13,8)] : true,
                [utils.asGridCoord(13,9)] : true, 
                [utils.asGridCoord(13,10)] : true,
                [utils.asGridCoord(13,11)] : true,

                [utils.asGridCoord(0,12)] : true, // outer bottom perimeter
                [utils.asGridCoord(1,12)] : true,
                [utils.asGridCoord(2,12)] : true,
                [utils.asGridCoord(3,12)] : true,
                [utils.asGridCoord(4,12)] : true,
                [utils.asGridCoord(5,12)] : true, 
                [utils.asGridCoord(6,13)] : true,
                [utils.asGridCoord(7,12)] : true,
                [utils.asGridCoord(8,12)] : true,
                [utils.asGridCoord(9,12)] : true,
                [utils.asGridCoord(10,12)] : true, 
                [utils.asGridCoord(11,12)] : true,
                [utils.asGridCoord(12,12)] : true,

                [utils.asGridCoord(0,1)] : true, // outer right perimeter
                [utils.asGridCoord(0,2)] : true,
                [utils.asGridCoord(0,3)] : true,
                [utils.asGridCoord(0,4)] : true, 
                [utils.asGridCoord(0,5)] : true,
                [utils.asGridCoord(0,6)] : true,
                [utils.asGridCoord(0,7)] : true,
                [utils.asGridCoord(0,8)] : true,
                [utils.asGridCoord(0,9)] : true, 
                [utils.asGridCoord(0,10)] : true,
                [utils.asGridCoord(0,11)] : true,

                //[utils.asGridCoord(3,7)] : true, // top left table
                [utils.asGridCoord(8,7)] : true, // top right table
                [utils.asGridCoord(3,10)] : true, // bottom left table
                [utils.asGridCoord(8,10)] : true, // bottom right table
                [utils.asGridCoord(11,5)] : true, // far right table

                [utils.asGridCoord(11,7)] : true, // front desk table
                [utils.asGridCoord(12,7)] : true, 

                [utils.asGridCoord(0,5)] : true, // bartender table
                [utils.asGridCoord(1,5)] : true,
                [utils.asGridCoord(2,5)] : true,
                [utils.asGridCoord(3,5)] : true,
                [utils.asGridCoord(4,5)] : true,

                [utils.asGridCoord(4,3)] : true, //block loop hole walls
                [utils.asGridCoord(5,3)] : true,
                [utils.asGridCoord(8,3)] : true,
                [utils.asGridCoord(9,4)] : true,
                [utils.asGridCoord(10,4)] : true,

                [utils.asGridCoord(6,4)] : true, //right bartender wall
                [utils.asGridCoord(6,5)] : true,
                
            }
        },
        Kitchen: {
            lowerSrc: "images/places/KitchenLower.png",
            upperSrc: "images/places/KitchenUpper.png",
            gameObjects: {
                hero: new GameObject({
                    x: 3,
                    y: 5,
                }),
                npcA: new GameObject({
                    x: 9,
                    y: 6,
                    src: "images/characters/mercenaryBeastman.png"
                }),
                npcB: new GameObject({
                    x: 10,
                    y: 8,
                    src: "images/characters/shadowelf.png"
                })
            }
        },
    }
