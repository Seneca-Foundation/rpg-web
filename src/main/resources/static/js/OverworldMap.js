class OverworldMap {
    constructor(config) {
        this.gameObjects = config.gameObjects;
        this.walls = config.walls || {}; // keeps track of wall objects in game

        this.lowerImage = new Image();
        this.lowerImage.src = config.lowerSrc; // lower layer-contains tiles floor where character will stand on

        this.upperImage = new Image();
        this.upperImage.src = config.upperSrc; // upper layers- rooftops and etc
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
        Object.values(this.gameObjects).forEach(o =>{

            //TODO: determine if this object should actually mount
            o.mount(this);
        })
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
                    x: utils.withGrid(7),
                    y: utils.withGrid(9),
                })
            },
            walls: {
                // "16,16" : true
                [utils.asGridCoord(7,6)] : true, // gives coords of walls
                [utils.asGridCoord(8,6)] : true,
                [utils.asGridCoord(7,7)] : true, // these numbers are for the table
                [utils.asGridCoord(8,7)] : true,
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
