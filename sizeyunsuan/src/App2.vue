<template>
  <div id="app">
    <h1 @click="change">{{msg}}</h1>
    <img src="./assets/logo.png">
    <router-view/>
  </div>
</template>

<script>
export default {
    name: 'App3',
    data(){
        var msg = 1;
        return {msg:msg}
    },
    methods: {
	    change() {
            this.msg = "nmsl"
		}
	}
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
