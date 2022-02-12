const path = require('path');
module.exports = {
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: 'ts-loader',
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.json'],
    alias: {
      assets: path.resolve(__dirname, "/assets/"),
      components: path.resolve(__dirname, "/components/"),
      pages: path.resolve(__dirname, "/pages/"),
      services: path.resolve(__dirname, "/services/"),
      styles: path.resolve(__dirname, "/styles/"),
      utils: path.resolve(__dirname, "/utils/")
    }
  },
  output: {
    filename: 'bundle.js',
    path: _resolve(__dirname, 'dist'),
  }
};