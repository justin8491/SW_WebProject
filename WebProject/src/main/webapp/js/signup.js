/**
 * 
 */

var success = document.querySelector(".fa-check");

var fail = document.querySelector(".fa-times");

const name = document.querySelector("#name");
const id = document.querySelector("#id");
const pwd1 = document.querySelector("#pwd");
const pwd2 = document.querySelector("#pwd_chenge_check");
const phone = document.querySelector("#phone3");
const email = document.querySelector("#email2");

name.addEventListener("blur", e => {
	const success = e.target.parentElement.querySelector(".fa-check");
	const fail = e.target.parentElement.querySelector(".fa-times");

	if (name.value != "" || name.value != null) {
		fail.style.display = "none";
		success.style.display = "block";
	} else {
		fail.style.display = "block";
		success.style.display = "none";
	}

})
id.addEventListener("blur", e => {
	const success = e.target.parentElement.querySelector(".fa-check");
	const fail = e.target.parentElement.querySelector(".fa-times");

	if (name.value != "" || name.value != null) {
		fail.style.display = "none";
		success.style.display = "block";
	} else {
		fail.style.display = "block";
		success.style.display = "none";
	}

})
pwd2.addEventListener("blur", e => {
	const success = e.target.parentElement.querySelector(".fa-check");
	const fail = e.target.parentElement.querySelector(".fa-times");

	if (name.value != "" || name.value != null || pwd1.value == pwd2.value) {
		fail.style.display = "none";
		success.style.display = "block";
		pwd2.parentElement.innerHTML("<p color='blue'>비밀번호가 일치합니다.</p>");
	} else if(name.value == "" || name.value == null || pwd1.value != pwd2.value){
		fail.style.display = "block";
		success.style.display = "none";
		pwd2.parentElement.append().innerHTML("<p color='red'>비밀번호가 같지 않습니다.</p>");
	}

})
phone.addEventListener("blur", e => {
	const success = e.target.parentElement.querySelector(".fa-check");
	const fail = e.target.parentElement.querySelector(".fa-times");

	if (name.value != "" || name.value != null) {
		fail.style.display = "none";
		success.style.display = "block";
	} else {
		fail.style.display = "block";
		success.style.display = "none";
	}

})
email.addEventListener("blur", e => {
	const success = e.target.parentElement.querySelector(".fa-check");
	const fail = e.target.parentElement.querySelector(".fa-times");

	if (name.value != "" || name.value != null) {
		fail.style.display = "none";
		success.style.display = "block";
	} else {
		fail.style.display = "block";
		success.style.display = "none";
	}

})

