class GameObject {
    constructor(config) {       //constructor to gather all necessary data for a game object
        this.x = config.x || 0; //Every game object will have a position on the map            access to properties ->gameObject: this **sprite line is where you give all the attributes of a sprite**
        this.y = config.y || 0;
        this.sprite = new Sprite({
            gameObject: this,
            src: config.src || "images/hero.png",
        });     // attributes of a character/sprite
    }
}