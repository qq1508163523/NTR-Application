module.exports = {
    pages: {
      index: {
          entry: 'src/main.js'
      }
    },
    lintOnSave: false,
    devServer: {
        proxy: {
            "/api/v1": {
                target: "http://localhost:8081",
            },
        },
    },
};
