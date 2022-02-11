<template>
  <div class="profile-pic-container">
    <div class="top-container">
      <el-row :gutter="12">
        <el-col :span="12">
          <div class="img-container">
            <div v-if="previewMode">
              <img ref="image" :src="src" alt="test">
              <div style="text-align: center;margin-top: 0.75rem">
                <el-link :underline="false" @click.native.prevent="selectFile">重新上传</el-link>
              </div>
            </div>
            <div v-else>
              <div class="img-select-block" @click="selectFile">
                <span style="font-size: 1.25rem">选择图片</span>
              </div>
            </div>
          </div>
          <input type="file"
                 accept="image/png, image/jpeg"
                 @change="initPreviewMode"
                 id="upload-file"
                 style="display: none">

          </input>
        </el-col>
        <el-col :span="12">
          <div class="preview-container">
            <img :src="destination" v-if="previewMode" alt="preview" class="img-preview">
            <img :src="$store.state.account.account.profilePicUrl"
                 v-if="!previewMode"
                 alt="current profile pic"
                 class="img-preview"
            >
            <div style="margin-top: 0.5rem;color: #bab5be" >
              <span>{{previewText}}</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="bottom-container">
      <div style="text-align: center;color: red">
        <span>支持JPG,PNG,JPEG文件</span>
      </div>
      <div style="text-align: center;margin-top: 0.75rem;margin-bottom: 1.75rem">
        <el-button type="primary" :disabled="!previewMode" @click="updateImage">更新</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import Cropper from 'cropperjs';
import 'cropperjs/dist/cropper.css'

export default {
  data(){
    return {
      previewMode: false,
      previewText: "当前头像",
      cropper: null,
      destination: {},
      options: {
        zoomable: true,
        aspectRatio: 1,
        viewMode: 1,
      },
      src: "",
      reader: new FileReader()
    }
  },
  methods: {
    selectFile(){
      document.getElementById('upload-file').click();
    },
    initPreviewMode(e){
      if(!e.target.files.length) return;
      this.reader.readAsDataURL(e.target.files[0]);
    },
    initCropper(){
      if(this.cropper === null){
        this.cropper = new Cropper(this.$refs.image, {
          ...this.options,
          crop: () => {
            const canvas = this.cropper.getCroppedCanvas();
            this.destination = canvas.toDataURL("image/jpeg");
          }
        });
      }else{
        this.cropper.replace(this.src,false);
      }
    },
    async updateImage(){
      const formData = new FormData();
      formData.append("base64Str",this.destination);
      let p = this.$processing(document.querySelector(".profile-pic-container"),"处理中");
      try{
        await this.$store.dispatch("account/updateProfileImage",formData);
        this.$message.success("上传成功");
      }catch(err){
        this.$message.error(err.message);
      }
      p.close();
    }
  },
  mounted() {
    this.reader.onload = () => {
      this.src = this.reader.result;
      this.previewMode = true;
      this.previewText = "头像预览";
      this.$nextTick(()=>{
        this.initCropper();
      });
    };
  }
}
</script>

<style lang="scss" scoped>

.profile-pic-container{
  > .top-container{
    margin-top: 1.25rem;
    .img-container{
      margin-right: 1.25rem;
      float: right;
      >div:first-of-type{
        height: 196px;
        width: 196px;
        .img-select-block{
          height: 196px;
          background-color: #d0c4c4;
          border: 1px dashed #b9b9c4;
          cursor: pointer;
          display: flex;
          justify-content: center;
          align-items: center;
          &:hover{
            border: 1px dashed blueviolet;
          }
        }
        img{
          max-width: 100%;
          height: auto;
        }
      }
    }

    .preview-container{
      width: 196px;
      height: 196px;
      display: flex;
      margin-left: 1.25rem;
      padding-left: 0.75rem;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      border-left: 1px solid #cec4c4;
      > img{
        width: 128px;
        height: 128px;
        vertical-align: middle;
        border-radius: 50%;
        border: 1px solid #e8e2e2;
      }
    }
  }
  > .bottom-container{
   margin-top: 1.75rem;
  }
}


</style>