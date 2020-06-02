<!DOCTYPE html>

<html lang="en">
<%@ taglib uri="https://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>

  <meta charset="utf-8">

  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>PC</title>



  <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />

  <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" />

  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&display=swap" rel="stylesheet">

  <link rel="stylesheet" type="text/css" href="css/local.css" />



  <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

  <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.rotapie.js"></script>
</head>

<body>



  <div id="wrapper">

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

      <div class="navbar-header">

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">

          <span class="sr-only">Toggle navigation</span>

          <span class="icon-bar"></span>

          <span class="icon-bar"></span>

          <span class="icon-bar"></span>

        </button>

         <a class="navbar-brand" href="index.html">Plagiarism <br>Checker</a>

      </div>

      <div class="collapse navbar-collapse navbar-ex1-collapse shadows">

        <ul class="nav navbar-nav side-nav shadows">

           <li><a href="index.html"> <img src="assets/images/icons/incustomer.png" id="input_img"> &nbsp;Home</a>
          </li>



        </ul>

        <ul class="nav navbar-nav navbar-left navbar-user" style="margin-left:10px;">

          <li>

            <form class="navbar-search" style="padding-top: 0px!important;margin:20px 0px 0px 20px;">

              <div id='button-holder'>

                <img src='assets/images/icons/search.png' />

              </div><input type="text" placeholder="Search" class="form-control" id='search-text-input'>

            </form>



          </li>



        </ul>

        



        <ul class="nav navbar-nav navbar-right navbar-user">





          <li class="dropdown messages-dropdown">

            <button type="submit" class="btn btn-md btn-primary" style="margin:20px;width:120px;">Logout</button>



          </li>









        </ul>

      </div>

    </nav>



    <div id="page-wrapper" class="inner-wrapper">



      <div class="row">
        <div class="col-lg-12">
          <h3 class="panel-title"><b>Results</b></h3>
        </div>
        <div class="col-lg-7">




          <div class="panel shadows panel-primary">



            <div class="panel-body ">

              <form role="form">
                <ul class="form-row">
                  <li>
                    <label for="lastname" class="col-md-3">

                      <span class="label-text">Scan Properties

                      </span></label>

                    <div class="col-md-1"> <span class="label-text">:</span></div>

                    <div class="col-md-7"></div>
                  </li>

                  <li>
                    <label for="lastname" class="col-md-3">

                      <span class="label-text">No. Of Words</span>

                    </label>

                    <div class="col-md-1"> <span class="label-text">:</span></div>

                    <div class="col-md-7">49</div>
                  </li>
                  <li>
                    <label for="lastname" class="col-md-3">

                      <span class="label-text">Result Found</span>

                    </label>

                    <div class="col-md-1"> <span class="label-text">:</span></div>

                    <div class="col-md-7">2</div>
                  </li>
                  
                </ul>

              </form>

            </div>

          </div>

        </div>


      </div>

      <div class="row">
        
        <div class="col-lg-7">




          <div class="panel shadows panel-primary">



            <div class="panel-body ">

              <form role="form">
                <ul class="form-row">
                
                
                <c:forEach items="${getLinkSFromGoogle}" var="getLink">
                
                
                  <li>
                    <label for="lastname" class="col-md-12">

                      <span class="label-text">${getLink}

                      </span></label>

                   

                   
                  </li>
				</c:forEach>
                 
                </ul>

              </form>

            </div>

          </div>

        </div>



        <div class="col-md-5">


          <div class="panel shadows panel-primary">



            <div class="panel-body">


              <form role="form">

                <ul class="form-row">
                  <li>

                    <label for="lastname" class="col-md-12">

                      <span class="label-text">Here Organised sentences and its URL from where it's  copied<br>
                      Ex: https://www.java.com</span>

                    </label>
                    <br>
                    <br>
                     <div class="col-md-7"> 
                      <br>
                    <br>
                      <button class="btn btn-md btn-success">Compare Text</button>
                     </div>

                  </li>
                  
                </ul>
              </form>
              </div>

          </div>

        </div>
      </div>

      <div class="row">
       <div class="col-md-12">

          <div class="panel shadows panel-primary">

            <div class="panel-body">
               <div class="row">

              <div class="col-md-6">
              <div id="pie"></div>
            </div>
            <div class="col-md-3">
              <span class="label-text" style="font-size: 30px;color:red;">20% Palagrism</span>
            </div>
            <div class="col-md-3">
              <span class="label-text" style="font-size: 30px;color:red;">80% Unique</span>
            </div>
          </div>

            </div>

          </div>

        </div>

      </div>

      <!-- tab -->
       <div class="row">

        <div class="col-md-6">


                                  <button type="submit" class="btn btn-md btn-success" style="margin:20px;width:200px;">Download Report</button>
                                </div>
                                  <div class="col-md-6">
                                   <form role="form">

                <ul class="form-row">
                  <li>

                    <label for="lastname" class="col-md-5">

                      <span class="label-text">Email To</span>

                    </label>

                    <div class="col-md-1"> <span class="label-text">:</span></div>

                    <div class="col-md-6"><input type="text" class="form-control"></div>

                  </li>
                </ul>
              </form>
            </div>
                            
</div>
</div>


    </div>

<script type="text/javascript">
  $(document).ready(function(){
  $('#pie').rotapie({
  slices: [
    { color: '#006673', percentage: 80 }, 
    { color: '#0294a8', percentage: 20 }
    
  ],
});
});
</script>



</body>

</html>