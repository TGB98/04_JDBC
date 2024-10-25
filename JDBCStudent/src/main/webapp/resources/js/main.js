const stdAdd = document.querySelector("#addForm");
const stdName = document.querySelector("[name=stdName]");

stdAdd.addEventListener("submit", e => {

  const inputName = stdName.value.trim();

  if(inputName.length === 0) {
    e.preventDefault();

    alert("이름은 필수 입력 값입니다.");
    stdName.focus();
  }
});

