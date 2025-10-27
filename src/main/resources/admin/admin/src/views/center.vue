<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
    >  
     <el-row>
                    <el-col :span="12">
           <el-form-item v-if="flag=='Student'"  label='学生姓名' prop="StudentName">
               <el-input v-model="ruleForm.StudentName"  placeholder='学生姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Student'"  label='学生手机号' prop="StudentPhone">
               <el-input v-model="ruleForm.StudentPhone"  placeholder='学生手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Student'"  label='学生身份证号' prop="StudentIdNumber">
               <el-input v-model="ruleForm.StudentIdNumber"  placeholder='学生身份证号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='Student'" label='学生头像' prop="StudentPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.StudentPhoto?ruleForm.StudentPhoto:''"
                         @change="StudentPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='Student'"  label='电子邮箱' prop="StudentEmail">
               <el-input v-model="ruleForm.StudentEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Teacher'"  label='心理老师姓名' prop="TeacherName">
               <el-input v-model="ruleForm.TeacherName"  placeholder='心理老师姓名' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Teacher'"  label='心理老师手机号' prop="TeacherPhone">
               <el-input v-model="ruleForm.TeacherPhone"  placeholder='心理老师手机号' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
             <el-form-item v-if="flag=='Teacher'" label='心理老师头像' prop="TeacherPhoto">
                 <file-upload
                         tip="点击上传照片"
                         action="file/upload"
                         :limit="3"
                         :multiple="true"
                         :fileUrls="ruleForm.TeacherPhoto?ruleForm.TeacherPhoto:''"
                         @change="TeacherPhotoUploadChange"
                 ></file-upload>
             </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item v-if="flag=='Teacher'"  label='电子邮箱' prop="TeacherEmail">
               <el-input v-model="ruleForm.TeacherEmail"  placeholder='电子邮箱' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Teacher'"  label='擅长' prop="TeacherShanchang">
               <el-input v-model="ruleForm.TeacherShanchang"  placeholder='擅长' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="12">
           <el-form-item v-if="flag=='Teacher'"  label='履历' prop="TeacherLvli">
               <el-input v-model="ruleForm.TeacherLvli"  placeholder='履历' clearable></el-input>
           </el-form-item>
         </el-col>

         <el-col :span="24">
             <el-form-item v-if="flag=='Teacher'"  label="心理老师详细介绍" prop="TeacherContent">
                 <editor
                         style="min-width: 200px; max-width: 600px;"
                         v-model="ruleForm.TeacherContent"
                         class="editor"
                         action="file/upload">
                 </editor>
             </el-form-item>
         </el-col>
         <el-form-item v-if="flag=='Admin'" label="学生名" prop="username">
             <el-input v-model="ruleForm.username"
                       placeholder="学生名"></el-input>
         </el-form-item>
         <el-col :span="12">
             <el-form-item v-if="flag!='Admin'"  label="性别" prop="sexTypes">
                 <el-select v-model="ruleForm.sexTypes" placeholder="请选择性别">
                     <el-option
                             v-for="(item,index) in sexTypesOptions"
                             v-bind:key="item.codeIndex"
                             :label="item.indexName"
                             :value="item.codeIndex">
                     </el-option>
                 </el-select>
             </el-form-item>
         </el-col>
         <el-col :span="24">
             <el-form-item>
                 <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
             </el-form-item>
         </el-col>
     </el-row>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isMobile,isPhone,isURL,checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
        ruleForm: {},
        flag: '',
        AdminFlag: false,
        // sexTypesOptions : [],
// 注册表 学生
    // 注册的类型字段 学生
        // 性别
        sexTypesOptions : [],
// 注册表 心理老师
    // 注册的类型字段 心理老师
        // 性别
        sexTypesOptions : [],
    };
  },
  mounted() {
    //获取当前登录学生的信息
    var table = this.$storage.get("sessionTable");
    this.sessionTable = this.$storage.get("sessionTable");
    this.role = this.$storage.get("role");
    if (this.role != "管理员"){
    }

    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
// 注册表 学生
// 注册表 心理老师
      } else {
        this.$message.error(data.msg);
      }
    });

// 注册表 学生 的级联表
// 注册表 心理老师 的级联表

      this.$http({
          url: `dictionary/page?page=1&limit=100&sort=&order=&dicCode=sex_types`,
          method: "get"
      }).then(({ data }) => {
          if (data && data.code === 0) {
              this.sexTypesOptions = data.data.list;
          } else {
              this.$message.error(data.msg);
          }
      });
  },
  methods: {
    StudentPhotoUploadChange(fileUrls) {
        this.ruleForm.StudentPhoto = fileUrls;
    },
    TeacherPhotoUploadChange(fileUrls) {
        this.ruleForm.TeacherPhoto = fileUrls;
    },


    onUpdateHandler() {
                         if((!this.ruleForm.StudentName)&& 'Student'==this.flag){
                             this.$message.error('学生姓名不能为空');
                             return
                         }

                             if( 'Student' ==this.flag && this.ruleForm.StudentPhone&&(!isMobile(this.ruleForm.StudentPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.StudentIdNumber)&& 'Student'==this.flag){
                             this.$message.error('学生身份证号不能为空');
                             return
                         }

                         if((!this.ruleForm.StudentPhoto)&& 'Student'==this.flag){
                             this.$message.error('学生头像不能为空');
                             return
                         }

                             if( 'Student' ==this.flag && this.ruleForm.StudentEmail&&(!isEmail(this.ruleForm.StudentEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.TeacherName)&& 'Teacher'==this.flag){
                             this.$message.error('心理老师姓名不能为空');
                             return
                         }

                             if( 'Teacher' ==this.flag && this.ruleForm.TeacherPhone&&(!isMobile(this.ruleForm.TeacherPhone))){
                                 this.$message.error(`手机应输入手机格式`);
                                 return
                             }
                         if((!this.ruleForm.TeacherPhoto)&& 'Teacher'==this.flag){
                             this.$message.error('心理老师头像不能为空');
                             return
                         }

                             if( 'Teacher' ==this.flag && this.ruleForm.TeacherEmail&&(!isEmail(this.ruleForm.TeacherEmail))){
                                 this.$message.error(`邮箱应输入邮箱格式`);
                                 return
                             }
                         if((!this.ruleForm.TeacherShanchang)&& 'Teacher'==this.flag){
                             this.$message.error('擅长不能为空');
                             return
                         }

                         if((!this.ruleForm.TeacherLvli)&& 'Teacher'==this.flag){
                             this.$message.error('履历不能为空');
                             return
                         }

                         if((!this.ruleForm.TeacherContent)&& 'Teacher'==this.flag){
                             this.$message.error('心理老师详细介绍不能为空');
                             return
                         }

        if((!this.ruleForm.sexTypes) && this.flag!='Admin'){
            this.$message.error('性别不能为空');
            return
        }
      if('Admin'==this.flag && this.ruleForm.username.trim().length<1) {
        this.$message.error(`学生名不能为空`);
        return	
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
