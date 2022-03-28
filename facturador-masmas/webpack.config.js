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
      adapters: path.resolve(__dirname, "/adapters/"),
      assets: path.resolve(__dirname, "/assets/"),
      components: path.resolve(__dirname, "/components/"),
      interceptors: path.resolve(__dirname, "/interceptors/"),
      models: path.resolve(__dirname, "/models/"),
      pages: path.resolve(__dirname, "/pages/"),
      services: path.resolve(__dirname, "/services/"),
      styledComponents: path.resolve(__dirname, "/styledComponents/"),
      utilities: path.resolve(__dirname, "/utilities/")
    }
  },
  output: {
    filename: 'bundle.js',
    path: _resolve(__dirname, 'dist'),
  }
};