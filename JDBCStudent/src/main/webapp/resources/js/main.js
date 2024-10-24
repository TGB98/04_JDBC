const stdAdd = document.querySelector("#addForm");
const stdName = document.querySelector("[name=stdName]");
const stdAge = document.querySelector("[name=stdAge]");
let stdGender = document.querySelectorAll("[name=stdGender]");
let chk_cnt = 0;
const stdScore = document.querySelector("[name=stdScore]");
stdAdd.addEventListener("submit", e => {

  const inputName = stdName.value.trim();
  const inputAge = stdAge.value.trim();

  if(inputName.length === 0) {
    e.preventDefault();

    alert("이름은 필수 입력 값입니다.");
    stdName.focus();
  }
});

