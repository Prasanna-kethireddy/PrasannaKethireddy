/*==================== MENU SHOW Y HIDDEN ====================*/
const navMenu = document.getElementById('nav-menu'),
      navToggle = document.getElementById('nav-toggle'),
      navClose = document.getElementById('nav-close');

/*===== MENU SHOW =====*/
if (navToggle) {
    navToggle.addEventListener('click', () => {
        navMenu.classList.add('show-menu');
        trapFocus(navMenu);
    });
}

/*===== MENU HIDDEN =====*/
if (navClose) {
    navClose.addEventListener('click', () => {
        navMenu.classList.remove('show-menu');
    });
}

/*==================== REMOVE MENU MOBILE ====================*/
const navLink = document.querySelectorAll('.nav__link');
function linkAction() {
    const navMenu = document.getElementById('nav-menu');
    navMenu.classList.remove('show-menu');
}
navLink.forEach(n => n.addEventListener('click', linkAction));

/*==================== ACCORDION SKILLS ====================*/
const skillsContent = document.getElementsByClassName('skills__content'),
      skillsHeader = document.querySelectorAll('.skills__header');

function toggleSkills() {
    let itemClass = this.parentNode.className;
    for (let i = 0; i < skillsContent.length; i++) {
        skillsContent[i].className = 'skills__content skills__close';
    }
    if (itemClass === 'skills__content skills__close') {
        this.parentNode.className = 'skills__content skills__open';
    }
}
skillsHeader.forEach((el) => el.addEventListener('click', toggleSkills));

/*==================== QUALIFICATION TABS (Improved + DOMContentLoaded) ====================*/
document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll(".qualification__button");
    const contents = document.querySelectorAll(".qualification__content");

    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            const target = document.querySelector(tab.dataset.target);
            tabs.forEach(t => t.classList.remove("qualification__active"));
            contents.forEach(c => c.classList.remove("qualification__active"));
            tab.classList.add("qualification__active");
            target.classList.add("qualification__active");
        });
    });
});

/*==================== PORTFOLIO SWIPER ====================*/
let swiper = new Swiper(".portfolio__container", {
    cssMode: true,
    spaceBetween: 30,
    loop: false, // Disable loop to avoid duplicating the single slide
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    on: {
        init: function () {
            // If only 1 slide (excluding duplicates), destroy swiper to make it static
            const actualSlides = this.slides.length;
            if (actualSlides <= 1) {
                this.destroy(true, true);
            }
        }
    }
});


/*==================== SCROLL SECTIONS ACTIVE LINK ====================*/
const sections = document.querySelectorAll('section[id]');
function scrollActive() {
    const scrollY = window.pageYOffset;
    sections.forEach(current => {
        const sectionHeight = current.offsetHeight;
        const sectionTop = current.offsetTop - 50;
        let sectionId = current.getAttribute('id');
        const link = document.querySelector('.nav__menu a[href*=' + sectionId + ']');
        if (link) {
            if (scrollY > sectionTop && scrollY <= sectionTop + sectionHeight) {
                link.classList.add('active-link');
            } else {
                link.classList.remove('active-link');
            }
        }
    });
}

/*==================== CHANGE BACKGROUND HEADER ====================*/
function scrollHeader() {
    const nav = document.getElementById('header');
    if (this.scrollY >= 80) nav.classList.add('scroll-header');
    else nav.classList.remove('scroll-header');
}

/*==================== SHOW SCROLL UP ====================*/
function scrollUp() {
    const scrollUp = document.getElementById('scroll-up');
    if (this.scrollY >= 560) scrollUp.classList.add('show-scroll');
    else scrollUp.classList.remove('show-scroll');
}

/*==================== DARK LIGHT THEME ====================*/
const themeButton = document.getElementById('theme-button');
const darkTheme = 'dark-theme';
const iconTheme = 'fa-sun';
themeButton?.setAttribute('aria-label', 'Toggle Dark Mode');

const selectedTheme = localStorage.getItem('selected-theme');
const selectedIcon = localStorage.getItem('selected-icon');
const getCurrentTheme = () => document.body.classList.contains(darkTheme) ? 'dark' : 'light';
const getCurrentIcon = () => themeButton.classList.contains(iconTheme) ? 'fa-moon' : 'fa-sun';

if (selectedTheme) {
    document.body.classList[selectedTheme === 'dark' ? 'add' : 'remove'](darkTheme);
    themeButton.classList[selectedIcon === 'fa-moon' ? 'add' : 'remove'](iconTheme);
}
themeButton?.addEventListener('click', () => {
    document.body.classList.toggle(darkTheme);
    themeButton.classList.toggle(iconTheme);
    localStorage.setItem('selected-theme', getCurrentTheme());
    localStorage.setItem('selected-icon', getCurrentIcon());
});

/*==================== SCROLL REVEAL ====================*/
const sr = ScrollReveal({
    origin: 'top',
    distance: '60px',
    duration: 2000,
    delay: 200,
    once: true
});
sr.reveal('.home__data, .about__img, .skills__subtitle, .skills__text');
sr.reveal('.home__img, .about__data, .skills__content, .portfolio__img', { delay: 400 });
sr.reveal('.home__social, .about__info, .portfolio__data, .contact__inputs', { delay: 600, interval: 200 });

/*==================== EMAILJS ====================*/
(function () {
    emailjs.init("mhmLLJmK6gjX3gqUI");
})();
const contactForm = document.getElementById('contact-form');
const contactMessage = document.getElementById('contact-message');

function validateForm() {
    const inputs = document.querySelectorAll('.contact__input');
    let isValid = true;
    inputs.forEach(input => {
        if (input.value.trim() === '') {
            input.style.borderColor = '#ff6b6b';
            isValid = false;
        } else {
            input.style.borderColor = 'transparent';
        }
        if (input.type === 'email') {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(input.value)) {
                input.style.borderColor = '#ff6b6b';
                isValid = false;
            }
        }
    });
    return isValid;
}

contactForm?.addEventListener('submit', (e) => {
    e.preventDefault();
    if (!validateForm()) {
        contactMessage.textContent = 'Please fill all fields correctly ❗';
        contactMessage.classList.add('error');
        setTimeout(() => contactMessage.textContent = '', 4000);
        return;
    }

    contactForm.classList.add('loading');
    emailjs.sendForm('service_ly60xea', 'template_aackhfc', '#contact-form', 'mhmLLJmK6gjX3gqUI')
        .then(() => {
            contactMessage.textContent = 'Message sent successfully ✅';
            contactMessage.classList.add('success');
            setTimeout(() => contactMessage.textContent = '', 5000);
            contactForm.reset();
        }, () => {
            contactMessage.textContent = 'Message not sent (service error) ❌';
            contactMessage.classList.add('error');
            setTimeout(() => contactMessage.textContent = '', 5000);
        }).finally(() => {
            contactForm.classList.remove('loading');
        });
});

/*==================== SMOOTH SCROLL ====================*/
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        const href = this.getAttribute('href');

        // Skip if href is just "#" or invalid
        if (href.length <= 1) return;

        const target = document.querySelector(href);
        if (target) {
            e.preventDefault();
            target.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
});



/*==================== PORTFOLIO FILTER ====================*/
const portfolioFilters = document.querySelectorAll('.portfolio__filter');
const portfolioItems = document.querySelectorAll('.portfolio__content');
portfolioFilters.forEach(filter => {
    filter.addEventListener('click', () => {
        portfolioFilters.forEach(f => f.classList.remove('active'));
        filter.classList.add('active');
        const filterValue = filter.getAttribute('data-filter');
        portfolioItems.forEach(item => {
            item.style.display = (filterValue === 'all' || item.classList.contains(filterValue)) ? 'block' : 'none';
            item.style.animation = 'fadeInUp 0.6s ease-out';
        });
    });
});

/*==================== PRELOADER ====================*/
window.addEventListener('load', () => {
    const preloader = document.querySelector('.preloader');
    if (preloader) {
        preloader.style.opacity = '0';
        setTimeout(() => {
            preloader.style.display = 'none';
        }, 500);
    }
});

/*==================== LAZY LOAD ====================*/
const images = document.querySelectorAll('img[data-src]');
const imageObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            const img = entry.target;
            img.src = img.dataset.src;
            img.setAttribute('loading', 'lazy');
            img.classList.remove('lazy');
            imageObserver.unobserve(img);
        }
    });
});
images.forEach(img => imageObserver.observe(img));

/*==================== CURSOR EFFECT ====================*/
const cursor = document.querySelector('.cursor');
const cursorFollower = document.querySelector('.cursor-follower');
if (cursor && cursorFollower) {
    document.addEventListener('mousemove', (e) => {
        cursor.style.left = e.clientX + 'px';
        cursor.style.top = e.clientY + 'px';
        setTimeout(() => {
            cursorFollower.style.left = e.clientX + 'px';
            cursorFollower.style.top = e.clientY + 'px';
        }, 100);
    });
    document.querySelectorAll('a, button, .button').forEach(el => {
        el.addEventListener('mouseenter', () => {
            cursor.classList.add('cursor-hover');
            cursorFollower.classList.add('cursor-hover');
        });
        el.addEventListener('mouseleave', () => {
            cursor.classList.remove('cursor-hover');
            cursorFollower.classList.remove('cursor-hover');
        });
    });
}

/*==================== ACCESSIBILITY & PERFORMANCE ====================*/
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        navMenu.classList.remove('show-menu');
    }
});
const focusableElements = 'button, [href], input, select, textarea, [tabindex]:not([tabindex="-1"])';
function trapFocus(element) {
    const focusableContent = element.querySelectorAll(focusableElements);
    const first = focusableContent[0];
    const last = focusableContent[focusableContent.length - 1];
    document.addEventListener('keydown', (e) => {
        if (e.key === 'Tab') {
            if (e.shiftKey && document.activeElement === first) {
                last.focus();
                e.preventDefault();
            } else if (document.activeElement === last) {
                first.focus();
                e.preventDefault();
            }
        }
    });
}

// Debounce function
const debounce = (func, wait) => {
    let timeout;
    return function (...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), wait);
    };
};
window.addEventListener('scroll', debounce(scrollActive, 10));
window.addEventListener('scroll', debounce(scrollHeader, 10));
window.addEventListener('scroll', debounce(scrollUp, 10));

console.log('Welcome to my World! 🖤👻🦇');
