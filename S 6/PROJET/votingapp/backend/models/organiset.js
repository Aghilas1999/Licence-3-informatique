const mongoose = require('mongoose')

const organiserTemplate = new mongoose.Schema({
    fullName:{
        type:String,
        required:true
    },
    address:{
        type:String,
        required:true
    },
    Email:{
        type:String,
        required:true
    },
    password:{
        type:String,
        required:true
    }
   
})


module.exports = mongoose.model('organiserTable',organiserTemplate)