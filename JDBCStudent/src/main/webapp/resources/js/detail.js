const stdNo = new URLSearchParams(location.search).get("stdNo");

const stdName = document.querySelector("[name=stdName]");
const stdAge = document.querySelector("[name=stdAge]");
let stdGender = document.querySelectorAll("[name=stdGender]");
let chk_cnt = 0;
const stdScore = document.querySelector("[name=stdScore]");

const goToList = document.querySelector("#goToList");

goToList.addEventListener("click", () => {

  location.href = "/";
});

const updateBtn = document.querySelector("#updateBtn");

updateBtn.addEventListener("click", () => {

  location.href = "/std/update?stdNo=" + stdNo;

});



const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", () => {

  if(!confirm("정말로 이 학생의 정보를 제거합니까?")) return;

  location.href = "/std/delete?stdNo=" + stdNo;
});