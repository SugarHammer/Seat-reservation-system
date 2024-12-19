<template>
  <div>
    <!--    编辑-->
    <el-dialog width="500px" title="修改用户" :visible.sync="updateFormIsShow">
      <el-form class="addform">
        <el-form-item label="用户名">
          <el-input v-model="currentUpdate.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="currentUpdate.nickName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="text" v-model="currentUpdate.password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateFormIsShow = false">取 消</el-button>
        <el-button type="primary" @click="updateUser()">修 改</el-button>
      </div>
    </el-dialog>
    <!--    确认删除-->
    <el-dialog
        title="确认删除"
        :visible.sync="dialogVisible"
        width="15%">
      <span>是否确认删除？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false ">取 消</el-button>
    <el-button type="primary" @click="isDelete">确 定</el-button>
  </span>
    </el-dialog>
    <!--    表格-->
    <div class="class-table">
      <div class="search-con">
        <el-button type="danger" @click="batchDeleteClick()">批量删除</el-button>
        <el-input
            class="search-class"
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="searchName"
            @keyup.enter.native="search">
        </el-input>
        <el-button type="primary" plain @click="search">搜索</el-button>
      </div>
      <el-table
          v-loading="loading"
          :data="tableData"
          style="width: 100%"
          @select=select
          @select-all=selectAll
      >
        <el-table-column
            type="selection"
        >
        </el-table-column>
        <el-table-column
            label="id"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="用户名"
        >
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.username }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="密码"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.password }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="角色"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.role }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="注册时间"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.createTime | dateFormat }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          :page-sizes="[1,10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change=sizeChange
          @current-change="currentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {adminUserData, deleteBatchUser,updateUser} from "../../network/adminUser";

export default {
  name: "AdminUser",
  data() {
    return {
      loading: true,
      dialogVisible: false,
      tableData: {},
      pageSize: 10,
      total: 1,
      searchName: '',
      currentPage: 1,
      selectRow: {},
      currentUpdate: {},
      updateFormIsShow: false
    }
  },
  methods: {
    updateUser(){
      const form = this.currentUpdate

      updateUser(form).then(res => {
        if (res.code === 2000){
          this.$message({
            message: '修改成功',
            type: 'success'
          });
        }
      })
      this.updateFormIsShow = false
      this.getTableData()
    },
    handleEdit(index, row) {
      this.currentUpdate = row
      this.updateFormIsShow = true
    },
    handleDelete(index, row) {
      this.selectRow = [row]
      this.dialogVisible = true
    },
    select(selection, row) {
      this.selectRow = selection
    }
    ,
    delete() {
      const ids = []
      for (let i = 0; i < this.selectRow.length; i++) {
        ids.push(this.selectRow[i].id)
      }
      deleteBatchUser(ids).then(res => {
        if (res.code === 2000) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          //重载表格
          this.getTableData()
        }
      })
    }
    ,
    isDelete() {
      if (this.selectRow.length != 0) {
        this.delete()
      }
      this.dialogVisible = false
    }
    ,
    batchDeleteClick() {
      this.dialogVisible = true
    }
    ,
    selectAll(selection) {
      this.selectRow = selection
      console.log(selection)
    }
    ,
    search() {
      const req = {}
      req.currentPage = this.currentPage
      req.pageSize = this.pageSize
      req.name = this.searchName

      this.getTableData()
    }
    ,
    sizeChange(size) {
      this.pageSize = size
      this.getTableData()
    }
    ,
    currentChange(size) {
      this.currentPage = size
      this.getTableData()
    },
    getTableData() {
      const req = {}
      req.currentPage = this.currentPage
      req.pageSize = this.pageSize
      req.name = this.searchName

      adminUserData(req).then(res => {
        this.tableData = res.data.records
        this.pageSize = res.data.size
        this.currentPage = res.data.current
        this.total = res.data.total
        this.loading = false
      })
    }
  },
  created() {
    this.getTableData()
  }
}
</script>

<style scoped>
.addform {
  padding: 0 50px;
}

.search-class {
  width: 200px;
  padding-right: 4px;
  margin-left: 10px;
}

.el-pagination {
  display: flex;
  justify-content: flex-end;
  margin-top: 30px;
}

.class-table {
  padding: 0 100px;
  margin-top: 40px;
}

.search-con {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  padding-bottom: 20px;
}

.el-menu-item.is-active {
  border-bottom-color: rgb(255, 208, 75);
  color: rgb(255, 208, 75);
}
</style>