// 할 일 상세 조회 페이지에서 쿼리스트링 값 얻어오기.
// url 에서 얻어오면 된다. (쿼리스트링 부분 ?todoNo=4)

// location.search : 쿼리스트링만 얻어오기.
// URLSearchParams : 쿼리스트링을 객체 형태로 다룰 수 있는 객체.

const todoNo = new URLSearchParams(location.search).get("todoNo"); // 할 일 번호.
// ?todoNo=4
// -> todoNo = 4

// console.log(todoNo);

// 목록으로 버튼 클릭 시 동작
const goToList = document.querySelector("#goToList");
// 목록으로 버튼 요소

// 목록으로 버튼이 클릭된 경우
goToList.addEventListener("click", () => {

  // "/" 메인페이지로 요청을 보냄. (GET 방식)
  location.href = "/";

});

// 완료 여부 변경 버튼 클릭 시 동작
const completeBtn = document.querySelector("#completeBtn");

// 완료 여부 변경 버튼이 클릭 된 경우
completeBtn.addEventListener("click", () => {

  // 현재 보고 있는 Todo의 완료 여부
  // O(true) <-> X(false) 변경 요청 하기.(GET 요청)
  location.href = "/todo/complete?todoNo=" + todoNo;

});

// 삭제 버튼 클릭 시 동작
const deleteBtn = document.querySelector("#deleteBtn");

deleteBtn.addEventListener("click", () => {

  // 1. 삭제할 것인지 confirm() 을 이용해서 재차 확인.
  // confirm() 은 확인 클릭 시 true, 취소 클릭 시 false 를 반환.

  // 취소 클릭 시
  if(!confirm("정말 삭제 하시겠습니까?")) return;

  // 확인 클릭 시
  // /todo/delete?todoNo=4 GET 방식 요청 보내기.
  location.href = "/todo/delete?todoNo=" + todoNo;

  // 삭제 성공 시 : "할 일이 삭제 되었습니다." alert 창 띄우기.
  // 삭제 실패 시 : "todo가 존재하지 않습니다." alert 창 띄우기.
  // 성공 / 실패 모두 메인 페이지로 redirect

});

// 수정 버튼 클릭 시 동작
const updateBtn = document.querySelector("#updateBtn");
updateBtn.addEventListener("click", () => {
  // 수정할 수 있는 화면을 요청. (GET 방식)
  location.href = "/todo/update?todoNo=" + todoNo;
});