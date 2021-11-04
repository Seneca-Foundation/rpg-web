class OverworldMap {
    constructor(config) {
        this.overworld = null;
        this.gameObjects = config.gameObjects;
        this.cutsceneSpaces = config.cutsceneSpaces || {};
        this.walls = config.walls || {}; // keeps track of wall objects in game

        this.lowerImage = new Image();
        this.lowerImage.src = config.lowerSrc; // lower layer-contains tiles floor where character will stand on

        this.upperImage = new Image();
        this.upperImage.src = config.upperSrc; // upper layers- rooftops and etc

        this.isCutscenePlaying = false;
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

        //Reset Npcs to do their idle behavior
        Object.values(this.gameObjects).forEach(object => object.doBehaviorEvent(this) )
    }

    checkForActionCutscene() {
        const hero = this.gameObjects["hero"];
        const nextCoords = utils.nextPosition(hero.x, hero.y, hero.direction);
        const match = Object.values(this.gameObjects).find(object => {
          return `${object.x},${object.y}` === `${nextCoords.x},${nextCoords.y}`
        });
        if (!this.isCutscenePlaying && match && match.talking.length) {
            this.startCutscene(match.talking[0].events)
          }
      }

      checkForFootstepCutscene() {
        const hero = this.gameObjects["hero"];
        const match = this.cutsceneSpaces[ `${hero.x},${hero.y}` ];
        if (!this.isCutscenePlaying && match) {
          this.startCutscene( match[0].events )
        }
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
                    x: utils.withGrid(6),
                    y: utils.withGrid(11),
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
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Where's the bathroom?!", faceHero: "npc1" },
                                {type: "textMessage", text: "Man oh man!"},
                                {who: "hero", type: "walk", direction: "down"},
                            ]
                        }
                    ]
                }),
                npc2: new Person({
                    x: utils.withGrid(4),
                    y: utils.withGrid(7),
                    src: "images/characters/panda.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left", time: 1500},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Damn, bro got the runs!", faceHero: "npc2" },
                                {type: "textMessage", text: "Sheeeesh!"},
                                {who: "hero", type: "walk", direction: "down"},
                            ]
                        }
                    ]
                }),
                npc3: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(10),
                    src: "images/characters/shadowLord.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Hmph.", faceHero: "npc3" },
                                {type: "textMessage", text: "Go away."},
                            ]
                        }
                    ]
                }),
                npc4: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(10),
                    src: "images/characters/shadowLordHenchmen.png",
                    behaviorLoop: [
                        {type: "stand", direction: "right"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Leave.", faceHero: "npc4" },
                            ]
                        }
                    ]
                }),
                npc5: new Person({
                    x: utils.withGrid(3),
                    y: utils.withGrid(4),
                    src: "images/characters/bartenderBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "down", time:1500},
                        {type: "stand", direction: "up", time:1500},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Man, these are a lot of glasses I need to put away. ", faceHero: "npc5" },
                                {type: "textMessage", text: "Sorry bucko, can't talk right now."},
                            ]
                        }
                    ]
                }),
                npc6: new Person({
                    x: utils.withGrid(8),
                    y: utils.withGrid(4),
                    src: "images/characters/mercenaryBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "down"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "I could really use a drink right now. ", faceHero: "npc6" },
                            ]
                        }
                    ]
                }),
                npc6B: new Person({
                    x: utils.withGrid(6),
                    y: utils.withGrid(13),
                    src: "images/characters/mercenaryBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "up"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "... ", faceHero: "npc6B" },
                                {type: "textMessage", text: "Backoff."},
                                {type: "textMessage", text: "Meow."},
                            ]
                        }
                    ]
                }),
                npc7: new Person({
                    x: utils.withGrid(12),
                    y: utils.withGrid(6),
                    src: "images/characters/femaleBartenderBeastman.png",
                    behaviorLoop: [
                        // {type: "stand", direction: "down"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Meow. ", faceHero: "npc7" },
                                {type: "textMessage", text: "Sorry. There's no tables available for you at this time."},
                                {type: "textMessage", text: "Meow."},
                            ]
                        }
                    ]
                }),
                npc8: new Person({
                    x: utils.withGrid(7),
                    y: utils.withGrid(7),
                    src: "images/characters/royaltyBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "down"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "I wonder what those two are plotting. ", faceHero: "npc8" },
                                {type: "textMessage", text: "Probably nothing good."},
                                {type: "textMessage", text: "Do you mind bothering someone else?"},
                                {who: "hero", type: "walk", direction: "left"},
                            ]
                        }
                    ]  
                }),
                npc9: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(7),
                    src: "images/characters/angel.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "I wonder why she left me... ", faceHero: "npc9" },
                                {type: "textMessage", text: "Oops, I mean uhhh..."},
                                {type: "textMessage", text: "Man, I have a bad habit of thinking out load."},
                            ]
                        }
                    ] 
                }),
                npc10: new Person({
                    x: utils.withGrid(2),
                    y: utils.withGrid(10),
                    src: "images/characters/doctorBeastmen.png",
                    behaviorLoop: [
                        {type: "stand", direction: "right"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "'Sad Gasp'.", faceHero: "npc10" },
                                {type: "textMessage", text: "Why am I always alone..."},
                            ]
                        }
                    ]
                }),
                musicPlayer: new Person({
                    x: utils.withGrid(12),
                    y: utils.withGrid(11),
                    src: "images/characters/musicBeastman.png",
                    behaviorLoop: [
                        {type: "walk", direction: "up", time: 800},
                        {type: "walk", direction: "up", time: 800},
                        {type: "walk", direction: "up", time: 800},
                        {type: "stand", direction: "up", time: 1200},
                        {type: "walk", direction: "down", time: 800},
                        {type: "walk", direction: "down", time: 800},
                        {type: "walk", direction: "down", time: 800},
                        {type: "stand", direction: "down", time: 1200},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Meow!", faceHero: "npc1" },
                                {type: "textMessage", text: "MEOW!"},
                                {type: "textMessage", text: "'COUGH'. 'COUGH'"},
                                {type: "textMessage", text: "Sussy baka."},
                                {who: "hero", type: "walk", direction: "left"},
                            ]
                        }
                    ]
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
                // [utils.asGridCoord(13,5)] : true, //glitch hole
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
                // [utils.asGridCoord(6,13)] : true,
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
                [utils.asGridCoord(3,7)] : true, // top left table
                [utils.asGridCoord(8,7)] : true, // top right table
                [utils.asGridCoord(3,10)] : true, // bottom left table
                [utils.asGridCoord(8,10)] : true, // bottom right table
                // [utils.asGridCoord(11,5)] : true, // far right table

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
            },
            cutsceneSpaces: {
                [utils.asGridCoord(7,3)]: [
                    {
                        events: [

                            { who: "npc6", type: "walk", direction: "left" },
                            { who: "npc6", type: "stand", direction: "up", time:500 },
                            {type: "textMessage", text:"You can't be in there  buckeroo!"},
                            { who: "npc6", type: "walk", direction: "right", },
                            { who: "npc6", type: "stand", direction: "down", time:500 },

                            { who: "hero", type: "walk", direction: "down" },
                            { who: "hero", type: "walk", direction: "down" },
                        ]
                    }
                ],
                [utils.asGridCoord(6,13)]: [
                    {
                        events: [
                            {type: "changeMap", map: "Backroom"}
                        ]
                    }
                ],
                [utils.asGridCoord(13,5)]: [
                    {
                        events: [

                            { who: "npc7", type: "stand", direction: "up", time:500 },
                            {type: "textMessage", text:"Woah! It seems you found a glitch in the game."},
                            {type: "textMessage", text:"Shhhhhhhh... The developer can't know of this."},
                            {type: "textMessage", text:"Come back and act like nothing happened."},
                            {type: "textMessage", text:"Meow."},
                            { who: "npc7", type: "stand", direction: "down", time:500 },

                            { who: "hero", type: "walk", direction: "left" },
                            { who: "hero", type: "walk", direction: "left" },
                        ]
                    }
                ]
            }
        },
        Backroom: {
            lowerSrc: "images/places/DemoLower.png",
            upperSrc: "images/places/DemoUpper.png",
            gameObjects: {
                hero: new Person({
                    isPlayerControlled: true,
                    x: utils.withGrid(5),
                    y: utils.withGrid(5),
                    src: "images/characters/shadowelf.png",
                }),
                npcA: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(6),
                    src: "images/characters/mercenaryBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "...", faceHero: "npcA" },
                            ]
                        }
                    ]
                }),
                npcB: new Person({
                    x: utils.withGrid(9),
                    y: utils.withGrid(7),
                    src: "images/characters/mercenaryBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "left"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "...", faceHero: "npcB" },
                            ]
                        }
                    ]
                }),
                npcC: new Person({
                    x: utils.withGrid(5),
                    y: utils.withGrid(4),
                    src: "images/characters/mercenaryBeastman.png",
                    behaviorLoop: [
                        {type: "stand", direction: "down"},
                    ],
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Don't go in there bro.", faceHero: "npcB" },
                            ]
                        }
                    ]
                })
            },
            walls: {
                // "16,16" : true
                [utils.asGridCoord(1,3)] : true, // outer perimeter top
                [utils.asGridCoord(2,3)] : true,
                [utils.asGridCoord(3,3)] : true,
                [utils.asGridCoord(4,3)] : true,
                [utils.asGridCoord(5,3)] : true, 
                [utils.asGridCoord(6,4)] : true,
                // [utils.asGridCoord(7,3)] : true, // back door
                [utils.asGridCoord(8,4)] : true,
                [utils.asGridCoord(9,3)] : true,
                [utils.asGridCoord(10,3)] : true, 

                [utils.asGridCoord(7,6)] : true, // table
                [utils.asGridCoord(7,7)] : true, 
                [utils.asGridCoord(8,6)] : true, 
                [utils.asGridCoord(8,7)] : true, 
                
                [utils.asGridCoord(11,4)] : true,// right perimeter
                [utils.asGridCoord(11,5)] : true,
                [utils.asGridCoord(11,6)] : true,
                [utils.asGridCoord(11,7)] : true,
                [utils.asGridCoord(11,8)] : true,
                [utils.asGridCoord(11,9)] : true,

                [utils.asGridCoord(1,10)] : true, // bottom perimeter 
                [utils.asGridCoord(2,10)] : true,
                [utils.asGridCoord(3,10)] : true,
                [utils.asGridCoord(4,10)] : true,
                // [utils.asGridCoord(5,10)] : true, // bottom door
                [utils.asGridCoord(6,10)] : true,
                [utils.asGridCoord(7,10)] : true, 
                [utils.asGridCoord(8,10)] : true,
                [utils.asGridCoord(9,10)] : true,
                [utils.asGridCoord(10,10)] : true, 

                [utils.asGridCoord(0,4)] : true,// left perimeter
                [utils.asGridCoord(0,5)] : true,
                [utils.asGridCoord(0,6)] : true,
                [utils.asGridCoord(0,7)] : true,
                [utils.asGridCoord(0,8)] : true,
                [utils.asGridCoord(0,9)] : true,  
            },
            cutsceneSpaces: {
                // [utils.asGridCoord(7,10)]: [
                //     {
                //         events: [

                //             { who: "npc6", type: "walk", direction: "left" },
                //             { who: "npc6", type: "stand", direction: "up", time:500 },
                //             {type: "textMessage", text:"You can't be in there  buckeroo!"},
                //             { who: "npc6", type: "walk", direction: "right", },
                //             { who: "npc6", type: "stand", direction: "down", time:500 },

                //             { who: "hero", type: "walk", direction: "down" },
                //             { who: "hero", type: "walk", direction: "down" },
                //         ]
                //     }
                // ],
                [utils.asGridCoord(7,3)]: [
                    {
                        events: [
                            {type: "changeMap", map: "Backdoor"}
                        ]
                    }
                ],
                [utils.asGridCoord(5,11)]: [
                    {
                        events: [
                            {type: "changeMap", map: "Trial"}
                        ]
                    }
                ]
            }
        },
        Trial: {
            lowerSrc: "images/places/7daytrial.png",
            upperSrc: "images/places/KitchenUpper.png",
            gameObjects: {
                hero: new Person({
                    isPlayerControlled: true,
                    x: utils.withGrid(5),
                    y: utils.withGrid(5),
                    src: "images/characters/shadowelf.png",
                }),
                npc: new Person({
                    x: utils.withGrid(10),
                    y: utils.withGrid(8),
                    src: "images/characters/mercenaryBeastman.png",
                    talking: [
                        {
                            events: [
                                {type: "textMessage", text: "Click Here to Begin!", faceHero: "npc" },
                            ]
                        }
                    ]
                })
            }
        },
        Backdoor: {
            lowerSrc: "images/places/pg2.png",
            upperSrc: "images/places/KitchenUpper.png",
            gameObjects: {
                hero: new Person({
                    isPlayerControlled: true,
                    x: utils.withGrid(5),
                    y: utils.withGrid(5),
                    src: "images/characters/shadowelf.png",
                }),
                // npcA: new Person({
                //     x: utils.withGrid(10),
                //     y: utils.withGrid(8),
                //     src: "images/characters/shadowelf.png",
                //     talking: [
                //         {
                //             events: [
                //                 {type: "textMessage", text: "You Made It!", faceHero: "npcA" },
                //             ]
                //         }
                //     ]
                // })
            },
            cutsceneSpaces: {
                // [utils.asGridCoord(7,10)]: [
                //     {
                //         events: [

                //             { who: "npc6", type: "walk", direction: "left" },
                //             { who: "npc6", type: "stand", direction: "up", time:500 },
                //             {type: "textMessage", text:"You can't be in there  buckeroo!"},
                //             { who: "npc6", type: "walk", direction: "right", },
                //             { who: "npc6", type: "stand", direction: "down", time:500 },

                //             { who: "hero", type: "walk", direction: "down" },
                //             { who: "hero", type: "walk", direction: "down" },
                //         ]
                //     }
                // ],
                [utils.asGridCoord(5,11)]: [
                    {
                        events: [
                            {type: "changeMap", map: "Trial"}
                        ]
                    }
                ]
                // [utils.asGridCoord(5,11)]: [
                //     {
                //         events: [
                //             {type: "changeMap", map: "Trial"}
                //         ]
                //     }
                // ]
            }
        },
    }
