<template>
  <div class="home">
    <!-- <h1>{{ msg }}</h1> -->
    <table id="testtable">
      <tr></tr>
    </table>
    <el-table :data="msg" height="415" >
      
          <el-table-column  prop="question" label="题目" ></el-table-column>
          <!-- <el-table-column  prop="answer"></el-table-column> -->
          <el-table-column label="答案">
          <template slot-scope="scope">
            <el-input v-model="scope.row.id" @focus="resetCount"></el-input>
            <span v-if="scope.row.id==scope.row.answer||count<0" style="color : green">{{scope.row.answer}}</span>
            <span v-else style="color : red" >wrong answer</span>
          </template>
          </el-table-column>
          
    </el-table>
    <h2>欢迎来到软导不软运算生成器 这里是您的用时</h2>
    <template>
      <div class="wrapper">
        <h1 id=mytime>{{str}}</h1>
        <!-- <input id="mytime" type="text" name="" value="显示时间"> -->
        <button id="stop" name="button" @click="stop">stop</button>
        <button id="start" name="button" @click="start">start</button>
        <button id="reset" name="button" @click="reset">reset</button>
      </div>
    </template>
    <div>
    <router-link to="/">回到主页</router-link>
    </div>
    <div>
    <template>
        <el-switch
          v-model="radio"
          active-color="#409EFF"
          inactive-color="#409EFF"
          active-text="分数"
          inactive-text="整数">
        </el-switch>
    </template>
    </div>
    <el-input-number  :max="999" :min="1" v-model="exp_input"></el-input-number>
    <el-input-number  :max="20" :min="2" v-model="num_input" placeholder="请输入表达式包含项数"></el-input-number>
  
  <el-form ref="form" :model="form" @submit.native="onsubmit" label-width="80px">
  <el-button type="primary" @click="onSubmit">生成题目</el-button>
  <el-button type="primary" @click="decCount">查看答案</el-button>
  </el-form>
  
  </div>
</template>

<script>
export default {
  name: 'home',
  data () {
    // var msg = 'welcome '
    return {
      h:0,//定义时，分，秒，毫秒并初始化为0；
      m:0,
      ms:0,
      s:0,
      time:0,
      str:'',
      mytime:'',
      radio:true,
      count:3,
      msg: [],
      exp_input: 1,
      num_input: 2,
      input:[],
      myresult:[],
      form: {
          name: '',
          exp: '',
          date1: '',
          date2: '',
          delivery: false,
          resource: '',
          desc: ''
        },
    }
  },
  methods: {
      decCount(){
        console.log(this.count)
        this.count=-1;
      },
      resetCount(){
        this.count = 3
      },
      judge(){
        for(let i in this.msg){
          console.log(this.msg[i].answer)
          console.log(this.msg[i].id)
          if(!this.msg[i].answer.equals(this.msg[i].id)){
              this.myresult.push(this.msg)
              console.log("myresult"+this.myresult)
          }
        }
      },
      onSubmit() {
        this.reset();
        this.start();
        this.resetCount();
        this.msg = [];
        var self = this;
        console.log(self.radio)
        if(!self.radio){
          console.log('普通')
        this.$ajax({
          method: 'get',
          url: '/exp/creatge',
          params: {
            expNum: self.exp_input,
            itemNum: self.num_input
          }
        }).then(function (response) {
          console.log(response);
           for(let index in response.data) {  
             self.msg.push(response.data[index])
            }; 
        }).catch(function (error) {
          console.log(error);
        });}
        else if(self.radio){
          console.log('分数')
          this.$ajax({
          method: 'get',
          url: '/exp/creatfr',
          params: {
            expNum: self.exp_input,
            itemNum: self.num_input
          }
        }).then(function (response) {
          console.log(response);
           for(let index in response.data) {  
             self.msg.push(response.data[index])
            }; 
        }).catch(function (error) {
          console.log(error);
        });
        }
    },
    timer(){   //定义计时函数
    this.ms=this.ms+50;         //毫秒
    if(this.ms>=1000){
      this.ms=0;
      this.s=this.s+1;         //秒
    }
    if(this.s>=60){
      this.s=0;
      this.m=this.m+1;        //分钟
    }
    if(this.m>=60){
      this.m=0;
      this.h=this.h+1;        //小时
    }
    this.str =this.toDub(this.h)+":"+this.toDub(this.m)+":"+this.toDub(this.s)+""/*+this.toDubms(this.ms)+"毫秒"*/;
    // document.getElementById('mytime').innerHTML=h+"时"+m+"分"+s+"秒"+ms+"毫秒";
  },
  reset(){  //重置
    clearInterval(this.time);
    this.h=0;
     this.m=0;
     this.ms=0;
     this.s=0;
    this.str="00:00:00";
  },
   start(){  //开始
     clearInterval(this.time);
     this.time=setInterval(this.timer,50);
  },
 
   stop(){  //暂停
    clearInterval(this.time);
  },
 
   toDub(n){  //补0操作
    if(n<10){
      return "0"+n;
    }
    else {
      return ""+n;
    }
  },
toDubms(n){  //给毫秒补0操作
    if(n<10){
      return "00"+n;
    }
    else {
      return "0"+n;
    }
 
  }

  }
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>