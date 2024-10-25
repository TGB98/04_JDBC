const stdAdd = document.querySelector("#addForm");
const stdName = document.querySelector("[name=stdName]");
const stdAge = document.querySelector("[name=stdAge]");
const stdGender = document.querySelectorAll("[name=stdGender]");

stdAdd.addEventListener("submit", e => {

  const inputName = stdName.value.trim();

  if(inputName.length === 0) {
    e.preventDefault();

    alert("이름은 필수 입력 값입니다.");
    stdName.focus();
  }
});

stdAdd.addEventListener("submit", e => {

  const inputAge = stdAge.value.trim();

  if(inputAge.length === 0) {
    e.preventDefault();

    alert("나이는 필수 입력 값입니다.");
    stdAge.focus();

 }
});

stdAdd.addEventListener("submit", e => {

	let sum = 0;
	for(let i = 0; i < stdGender.length; i++) {
		if(stdGender[i].checked == false) {
			sum += sum;
		}
		else {
			sum += sum+1;
		}
	}
	if(sum == 0) {
		e.preventDefault();
		alert("성별을 선택해주세요.");
		return false;
	}
	else {
		stdGender.submit();
	}

});