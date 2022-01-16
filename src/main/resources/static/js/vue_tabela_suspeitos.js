function onLoad(){
	var app = new Vue({
		el:"#suspeitos",
		data: {
			veiculos_suspeitos:[]
		},
		mounted(){
			axios.get("http://localhost:8078/api/veiculos/suspeitos")
			.then(response=>{
				 this.veiculos_suspeitos=response.data;
				 
			})
		}
	})
}
