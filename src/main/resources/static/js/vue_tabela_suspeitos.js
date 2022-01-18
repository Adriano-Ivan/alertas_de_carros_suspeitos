function onLoad(){
	var app = new Vue({
		el:"#suspeitos",
		data: {
			veiculos_suspeitos:[]
		},
		mounted(){
			axios.get("http://localhost:8078/api/veiculos/suspeitos")
			.then(response=>{
				 response.data.forEach((v, i)=>{
				 	v.enumerador=i+1;
				 });
				 this.veiculos_suspeitos=response.data;
			})
		}
	})
}
