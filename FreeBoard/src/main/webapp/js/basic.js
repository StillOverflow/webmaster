console.log("basic.js");

let obj = {
	name: "홍길동"
	,age: 20
	,showInfo: function(){
		return this.name + " : " + this.age;
	}
}
let data = [obj];
data.push({name: "박박", age: 25});
data.push({name: "서서", age: 32});

console.log(obj.showInfo());
console.log(obj);
console.log(data);

//DOM
let ul = document.createElement("ul");
document.body.appendChild(ul);
let li1 = document.createElement("li");
li1.innerText = "체리";
ul.appendChild(li1);
let li2 = document.createElement("li");
li2.innerText = "사과";
ul.appendChild(li2);

let lis = document.querySelectorAll("li");
for(let i = 0; i < lis.length; i++){
	lis[i].addEventListener("click", (e) => {
		let li = e.target;
		ul.removeChild(li);
	});
}

let tbody = document.querySelector("tbody");

for(let i = 0; i < data.length; i++){
	let tr = document.createElement("tr");
	let td_name = document.createElement("td");
	let td_age = document.createElement("td");
	td_name.innerText = data[i].name;
	td_age.innerText = data[i].age;
	tbody.appendChild(tr);
	tr.appendChild(td_name);
	tr.appendChild(td_age);
}
