const HOST = "http://localhost:8080";

const price = document.querySelector("#productPrice");
$(document).ready(function(){
  $("#productSelect").change(function(){
      const selectedProduct = $(this).children("option:selected").val();
      price.innerHTML = selectedProduct;
  });
});

const createOrder = () => {
  // getting the value from the from
  const productSelect = document.querySelector("#productSelect").value;

  const amount = document.querySelector("#amount").value;

  const productPrice = document.querySelector("#productPrice").textContent;

  const customerID = document.querySelector("#customerIDInput").value;

  const productID = document.querySelector("#productIDInput").value;


  $.ajax({
    method: "post",
    url: `${HOST}/carwash/orders`,
    data: JSON.stringify({
      "productSelect": productSelect,
      "productPrice":productPrice,
      "amount": amount,
      "customerID": customerID,
      "productID": productID
    }),
    crossDomain: true,
    headers: {
     'Accept': "application/json",
      'Content-type': "application/json",
    },
  })
    .done((response) => {
      alert('successful ordered');
    })
    
    .fail((obj, textStatus) => {
    
      if ((obj && obj.responseText)) {
        alert(obj.responseText);
        if(obj.status === 409){
          alert('There is a conflict !!!!');
        }
      }
      else{
        alert("general exception");
      } 
    });
};



const btnSubmit = document.querySelector("#submitbtn");
btnSubmit.addEventListener("click", createOrder);

