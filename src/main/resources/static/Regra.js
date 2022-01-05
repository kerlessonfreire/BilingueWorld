class mobileNavbar {
    constructor() {
        this.mobileMenu = document.querySelector(mobileMenu);
        this.navList = document.querySelector(navList);
        this.navLists = document.querySelectorAll(navLists);
        this.activeClass = "active";
        
        this.handleClick = this.handleClick.bind(this);
    }
    handleClick() {
        console.log(this);
        this.navList.classList.toggle(this.activeClass);
    }

    addClickEvent() {
        this.mobileMenu.addEventListener("click", this.handleClick);
    }
    init() {
        if (this.mobileMenu) {
            this.addClickEvent()
        }
        return this;
    }
}

const mobileNavbar = mobileNavbar(
    ",mobile-menu",
    ",nav-list",
    ",nav-list li",
);
mobileNavbar.init();