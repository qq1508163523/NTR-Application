<template>
  <div class="index-table-container">
    <div class="genre">
      <div class="el-row">
        <div class="el-col-2">
          <span>类型</span>
        </div>
        <div class="el-col-22">
          <ul>
            <li :class="checkActive('genre',genre.genreName)"
                v-for="genre in category.genreList"
                :key="genre.genreName"
                @click="doChange('genre',genre.genreName)"
            >
              {{genre.genreName}}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="year">
      <div class="el-row">
        <div class="el-col-2">
          <span>年代</span>
        </div>
        <div class="el-col-22">
          <ul>
            <li :class="checkActive('year',year)"
                v-for="year in category.yearList"
                :key="year"
                @click="doChange('year',year)"
            >
              {{year}}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="month">
      <div class="el-row">
        <div class="el-col-2">
          <span>季度</span>
        </div>
        <div class="el-col-22">
          <ul>
            <li :class="checkActive('month',month)"
                v-for="month in category.monthList"
                :key="month"
                @click="doChange('month',month)"
            >
              {{month | mapCategory('月')}}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="status">
      <div class="el-row">
        <div class="el-col-2">
          <span>状态</span>
        </div>
        <div class="el-col-22">
          <ul>
            <li :class="checkActive('status',status)"
                v-for="status in category.statusList"
                :key="status"
                @click="doChange('status',status)"
            >
              {{status}}
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="sort">
      <div class="el-row">
        <div class="el-col-2">
          <span>排序</span>
        </div>
        <div class="el-col-22">
          <ul>
            <li class="category-active" v-for="sort in category.sortList" :key="sort">{{sort}}</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "IndexTable",
  computed: {
    category(){
      return this.$store.state.category.indexTable;
    }
  },
  methods: {
    checkActive(categoryName,val){
      if(val === '全部'){
        return this.$store.state.category.categoryQuery[categoryName] === null?'category-active':'';
      }
      return this.$store.state.category.categoryQuery[categoryName] === val?'category-active':'';
    },
    doChange(categoryName,val){
      this.$store.state.category.categoryQuery[categoryName] = (val === '全部'?null:val);
      this.$store.state.category.categoryQuery['from'] = 1;
      this.$store.dispatch("category/getCategoryResult");
      this.$bus.$emit('PaginationReset')
    }
  }
}
</script>

<style lang="scss" scoped>

.index-table-container{
  div{
    margin: 0.25rem 0;
  }
  >.year,.month,.genre,.status,.sort{
    > .el-row{
      div:first-of-type{
        font-size: 1rem;
        text-align: center;
        padding: 0.25rem 0;
        font-weight: bold;
      }

      div:last-of-type{
        font-size: 1rem;
        > ul{
          > .category-active{
            background: orange;
            color: white;
          }
           > li{
             display: inline-block;
             padding: 0.25rem 0.5rem;
             margin: 0 0.1rem;
             border-radius: 5px;
             cursor: pointer;
             color: #6b5c5c;
            &:hover{
              //background: #a6a2a2;
              //color: white;
            }
          }

        }
      }
    }
  }
}

</style>