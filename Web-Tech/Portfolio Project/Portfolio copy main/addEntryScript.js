const form = document.getElementById('entryForm');
const title = document.getElementById('title');
const post = document.getElementById('post');

const clearButton = document.getElementById("clear");
clearButton.onclick = function(){
    if (confirm("Are you sure you want to clear title and text of blog post")){
        form.reset();
    }
}

form.addEventListener('submit', function(event){
    if (form.title.value == ""){
        event.preventDefault();
        document.getElementById('title').style.borderColor = "red";
        document.getElementById('error1').innerHTML = "Cannot leave title blank";
        setTimeout(function(){ 
            document.getElementById("error1").innerHTML = "";
        }, 3000);
    }
    else{
        document.getElementById('title').style.borderColor = "black";
        document.getElementById("error1").innerHTML = "";
    }
    if (form.post.value == ""){
        event.preventDefault();
        document.getElementById('post').style.borderColor = "red";
        document.getElementById('error2').innerHTML = "Cannot leave post blank";
        setTimeout(function(){ 
            document.getElementById("error2").innerHTML = "";
        }, 3000);
    }
    else{
        document.getElementById('post').style.borderColor = "black";
        document.getElementById("error2").innerHTML = "";
    }
});

