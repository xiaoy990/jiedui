<template>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="exp"
        label="题目"
        width="180">
      </el-table-column>
      <el-table-column
        prop="answer"
        label="答案"
        width="180">
      </el-table-column>
    </el-table>
  </template>

  <script>
    export default {
      data: function(args) {
        return {
          tableData: [{
            exp: args.question,
            answer: args.answer,
          }]
        }
      }
    }
  </script>