class SceneTransition {
    constructor() {
      this.element = null;
    }
    //Creates element of fading//receives style of SceneTransition css
    createElement() {
      this.element = document.createElement("div");
      this.element.classList.add("SceneTransition");
    }
    //triggers animation to fully opague then opacity 0
    fadeOut() {
      this.element.classList.add("fade-out");
      this.element.addEventListener("animationend", () => {
        this.element.remove();
      }, { once: true })
    }
  
    init(container, callback) {
      this.createElement();
      container.appendChild(this.element);
  
      this.element.addEventListener("animationend", () => {
        callback();
      }, { once: true })
  
    }
  }