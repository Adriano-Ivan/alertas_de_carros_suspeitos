
const elemento = document.querySelector("#mostrar-hora");

const exibir = function(){
	const momento = new Date();
	
	const hora = `${String(momento.getHours()).padStart(2,'0')}`;
	const minuto =  `${String(momento.getMinutes()).padStart(2,'0')}`;
	const segundo =  `${String(momento.getSeconds()).padStart(2,'0')}`;
	
	const exibicao = `${hora}:${minuto}:${segundo}`;
	
	elemento.textContent=exibicao;
	
}

exibir();
setInterval(exibir,1000);