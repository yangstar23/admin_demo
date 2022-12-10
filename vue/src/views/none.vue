<template>
<!--  新增用户-->
  <br>
  <el-button  @click="AddNew" type="success" plain>新增用户</el-button>
  <br>
  <br>
  <!-- Form -->
  <el-dialog v-model="dialogFormVisible" title="新增用户">
    <el-form :model="form">
      <el-form-item label="ID" :label-width="formLabelWidth">
        <el-input v-model="form.id" autocomplete="off" />
      </el-form-item>
      <el-form-item label="用户名" :label-width="formLabelWidth">
        <el-input v-model="form.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form.password" autocomplete="off" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>

<!--  展示表单数据-->
  <el-table :data="state.tableData" style="width: 100%">
    <el-table-column prop="id" label="ID" width="180" />
    <el-table-column prop="username" label="用户名" width="180" />
    <el-table-column prop="password" label="密码" />
    <!--编辑,删除按钮-->
    <el-table-column fixed="right" label="操作" width="120">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick"
        > <el-icon :size="size" :color="color">
          <Edit />
        </el-icon>编辑</el-button
        >
        <el-button link type="danger" size="small"> <el-icon :size="size" :color="color">
          <Delete />
        </el-icon>删除</el-button>
      </template>
    </el-table-column>

  </el-table>
</template>

<script setup>
import {reactive,ref} from "vue";
import request from "../request.js";
import {Edit,Delete} from '@element-plus/icons-vue'

const dialogTableVisible = ref(false)
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'
const AddNew=()=>{
  dialogFormVisible.value=true;
}
const form = reactive({
  id: '',
  username: '',
  password: '',
})

const state = reactive({
  tableData: [],
})

request.get("/user").then(res=>{
  state.tableData =res;
})
</script>