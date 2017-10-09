const webpack = require('webpack');
const path = require("path");
const bourbon = require("node-bourbon").includePaths;
const neat = require("node-neat").includePaths;
const ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
    entry: "./index.js",
    output: {
        filename: "bundle.js",
        path: path.join(__dirname, ".." , "public", "javascripts")
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                loader: "babel-loader",
                exclude: [/node_modules/]
            },
            {
                test: /\.[s]?css$/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: [
                        { loader: "css-loader", options: { modules: true, localIdentName: "[path][name]__[local]--[hash:base64:5]" }},
                        { loader: "sass-loader", options: { includePaths: [bourbon, neat] }}
                    ]
                }),
                exclude: [/node_modules/]
            }
        ]
    },
    plugins: [
        new ExtractTextPlugin("styles.css")
    ]
};