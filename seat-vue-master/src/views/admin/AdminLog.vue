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
    <!--    回复表单-->
    <el-dialog @close="close" width="500px" title="回复留言" :visible.sync="addFormIsShow">
      <el-form :model="addForm" class="addform">
        <el-form-item label="回复内容">
          <el-input type="textarea" v-model="addForm.message"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormIsShow = false">取 消</el-button>
        <el-button type="primary" @click="addClass()">回 复</el-button>
      </div>
    </el-dialog>
    <!--    表格-->
    <div class="class-table">
      <div class="search-con">
        <el-button type="danger" @click="batchDeleteClick()">批量删除</el-button>
      </div>
      <el-table
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
            label="留言内容"
        >
          <template slot-scope="scope">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.content }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column
            label="留言用户"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="回复人"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.replayName }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="回复内容"
        >
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.replay }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">回复
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
import {getList, reply,deleteBatch} from "../../network/reply";

export default {
  name: "AdminLog",
  data() {
    return {
      pageSize: 10,
      total: 0,
      currentPage: 1,
      tableData: [],
      addFormIsShow: false,
      addForm: {},
      replyRow: {},
      selectRow: [],
      dialogVisible: false
    }
  },
  created() {
    const data = {
      currentPage: this.currentPage,
      pageSize: this.pageSize
    }
    getList(data).then(res => {
      this.tableData = res.data.records
      this.total = res.data.size
    })
  },
  methods: {
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
          const data = {
            currentPage: this.currentPage,
            pageSize: this.pageSize
          }
          getList(data).then(res => {
            this.tableData = res.data.records
            this.total = res.data.size
          })
        }
      })
    },
    batchDeleteClick(){
      this.dialogVisible = true
    },
    isDelete() {
      if (this.selectRow.length !== 0) {
        this.delete()
      }
      this.dialogVisible = false
    },
    select(selection, row) {
      this.selectRow = selection
    },
    selectAll(selection) {
      this.selectRow = selection
    },
    addClass() {
      const replyData = {
        id: this.replyRow.id,
        content: this.addForm.message
      }
      reply(replyData).then(res => {
        if (res.code === 2000) {
          this.$message({
            message: '回复成功',
            type: 'success'
          });
        } else {
          this.$message.error('回复失败');
        }

        const data = {
          currentPage: this.currentPage,
          pageSize: this.pageSize
        }
        getList(data).then(res => {
          this.tableData = res.data.records
          this.total = res.data.size
        })

        this.addFormIsShow = false
      })
    },
    close() {
      this.addForm.message = ""
    },
    handleEdit(index, row) {
      this.replyRow = row
      this.addFormIsShow = true
    }
  }
}
</script>

<style scoped>

</style>