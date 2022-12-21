let tbody = document.querySelector("tbody");
let tr = document.querySelector("tfoot tr");
let insertFile = document.querySelector(".insertFile");

function insertFileEventHandler() {
	let newTr = tr.cloneNode(true);
	tbody.append(newTr);
	newTr.style.display = "";

	newTr.querySelector(".insertFile").addEventListener("click", insertFileEventHandler);
	newTr.querySelector(".deleteFile").addEventListener("click", e => {
		tbody.removeChild(e.target.parentNode.parentNode)
	});
}

insertFile.addEventListener("click", insertFileEventHandler);


let boardForm = document.querySelector("#boardForm");
boardForm.addEventListener("submit", (e) => {
	e.preventDefault();

	fetch("/WebProject/board/boardReply.do",
		{
			method: 'POST',
			cache: 'no-cache',
			body: new FormData(boardForm)
		})
		.then(response => response.json())
		.then(jsonResult => {
			alert(jsonResult.message);
			if (jsonResult.status == true) {
				location.href = jsonResult.url;
			}
		});
});



