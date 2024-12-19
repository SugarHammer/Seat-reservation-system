<template>
  <div>
    <!--    确认删除-->
    <el-dialog
        title="确认删除"
        :visible.sync="dialogVisible"
        width="15%">
      <span>是否确认删除？</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="close">取 消</el-button>
    <el-button type="primary" @click="isDelete">确 定</el-button>
  </span>
    </el-dialog>
    <!--    编辑-->
    <el-dialog width="500px" title="修改自习室" :visible.sync="updateFormIsShow">
      <el-form :model="addForm" class="addform">
        <el-form-item label="自习室名称">
          <el-input v-model="currentUpdate.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="排数">
          <el-input v-model="currentUpdate.rows" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="总容量">
          <el-input type="number" v-model="currentUpdate.totalNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="开放时间-关闭时间">
          <el-date-picker
              v-model="updateTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否开启">
          <el-checkbox label="开启" v-model="addForm.isOpen"></el-checkbox>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateFormIsShow = false">取 消</el-button>
        <el-button type="primary" @click="updateClass()">修 改</el-button>
      </div>
    </el-dialog>
    <!--    添加表单-->
    <el-dialog width="500px" title="添加自习室" :visible.sync="addFormIsShow">
      <el-form :model="addForm" class="addform">
        <el-form-item label="自习室名称">
          <el-input v-model="addForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="总容量">
          <el-input type="number" v-model="addForm.totalNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="开放时间-关闭时间">
          <el-date-picker
              v-model="addForm.openTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否开启">
          <el-checkbox label="开启" v-model="addForm.isOpen"></el-checkbox>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormIsShow = false">取 消</el-button>
        <el-button type="primary" @click="addClass()">添 加</el-button>
      </div>
    </el-dialog>
    <!--    表格-->
    <div class="class-table">
      <div class="search-con">
        <el-button type="danger" @click="batchDeleteClick()">批量删除</el-button>
        <el-input
            class="search-class"
            placeholder="请输入内容"
            prefix-icon="el-icon-search"
            v-model="name"
            @keyup.enter.native="search">
        </el-input>
        <el-button type="primary" plain @click="search">搜索</el-button>
        <el-button type="success" @click="addFormIsShow = true">新增</el-button>
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
            label="自习室名称"
        >
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="已预约人数"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.checkedNum }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="自习室容量"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.totalNum }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="开放时间"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.openTime | dateFormat }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="关闭时间"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.closeTime | dateFormat }}</span>
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
import {adminClassData, addClass, deleteBatch,updateClass} from "../../network/adminClass";
import {timestampToTime} from "../../utils/timeUtils";

export default {
  name: "AdminClass",
  data() {
    return {
      loading: true,
      updateTime:[],
      updateFormIsShow: false,
      addFormIsShow: false,
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      name: '',
      dialogVisible: false,
      selectRow: [],
      addForm: {
        name: '',
        row: 0,
        totalNum: 0,
        openTime: [],
        closeTime: "",
        isOpen: true
      },
      currentUpdate: ''
    }
  },
  methods: {
    updateClass(){
      const form = this.currentUpdate
      form.openTime = this.updateTime

      updateClass(form).then(res => {
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
      this.updateTime.splice(0,1,row.openTime)
      this.updateTime.splice(1,2,row.closeTime)
      this.currentUpdate = row
      this.updateFormIsShow = true
    },
    addClass() {
      addClass(this.addForm).then(res => {
        if (res.code === 2000) {
          this.$message({
            message: '添加成功',
            type: 'success'
          });
        } else {
          this.$message.error('添加失败');
        }

        this.addFormIsShow = false
        this.getTableData()
      })
    },
    close() {
      this.selectRow = []
      this.dialogVisible = false
    },
    batchDeleteClick() {
      this.dialogVisible = true
    },
    search() {
      this.getTableData()
    },
    sizeChange(size) {
      this.pageSize = size
      this.getTableData()
    },
    currentChange(current) {
      this.currentPage = current
      this.getTableData()
    },
    getTableData() {
      //请求表格数据
      const req = {}
      req.name = this.name
      req.currentPage = this.currentPage
      req.pageSize = this.pageSize
      adminClassData(req).then(res => {
        this.tableData = res.data.records
        this.pageSize = res.data.size
        this.currentPage = res.data.current
        this.total = res.data.total
        this.loading = false
      })
    },
    select(selection, row) {
      this.selectRow = selection
    },
    selectAll(selection) {
      this.selectRow = selection
    },
    handleDelete(index, row) {
      this.selectRow = [row]
      this.dialogVisible = true
    },
    isDelete() {
      if (this.selectRow.length != 0) {
        this.delete()
      }
      this.dialogVisible = false
    },
    delete() {
      const ids = []
      for (let i = 0; i < this.selectRow.length; i++) {
        ids.push(this.selectRow[i].id)
      }
      deleteBatch(ids).then(res => {
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
  },
  created() {
    //请求表格数据
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