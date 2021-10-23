class OverworldMap {
    constructor(config) {
        this.gameObjects = config.gameObjects;

        this.lowerImage = new Image();
        this.lowerImage.src = config.lowerSrc; // lower layer-contains tiles floor where character will stand on

        this.upperImage = new Image();
        this.upperImage.src = config.upperSrc; // upper layers- rooftops and etc
    }

    drawLowerImage(ctx) {
        ctx.drawImage(this.lowerImage, 0, 0) // draws lower images
    }

    drawUpperImage(ctx) {
        ctx.drawImage(this.upperImage, 0, 0) // draws upper images
    }
}
    window.OverworldMaps = {
        DemoRoom: {
            lowerSrc: "images/DemoLower.png",
            upperSrc: "images/DemoUpper.png",
            gameObjects: {
                hero: new Person({
                    isPlayerControlled: true,
                    x: utils.withGrid(5),
                    y: utils.withGrid(6),
                     src: "images/shadowelf.png"
                }),
                npc1: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(9),
                })
            }
        },
        Kitchen: {
            lowerSrc: "images/KitchenLower.png",
            upperSrc: "images/KitchenUpper.png",
            gameObjects: {
                hero: new GameObject({
                    x: 3,
                    y: 5,
                }),
                npcA: new GameObject({
                    x: 9,
                    y: 6,
                    src: "images/test.png"
                }),
                npcB: new GameObject({
                    x: 10,
                    y: 8,
                    src: "images/shadowelf.png"
                })
            }
        },
    }
