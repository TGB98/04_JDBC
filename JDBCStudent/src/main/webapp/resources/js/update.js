const stdUpdate = document.querySelector('#updateForm');

const stdName = document.querySelector("[name=stdName]");
const stdAge = document.querySelector("[name=stdAge]");

stdUpdate.addEventListener("submit", e => {

  const intputName = stdName.value.trim();

  if(intputName.length === 0) {
    e.preventDefault();

    alert("이름은 필수 입력 값입니다.");
    stdName.focus();

  }

});

stdUpdate.addEventListener("submit", e => {

  const inputAge = stdAge.value.trim();

  if(inputAge.length === 0) {
    e.preventDefault();

    alert("나이는 필수 입력 값입니다.");
    stdAge.focus();

  }
  
});