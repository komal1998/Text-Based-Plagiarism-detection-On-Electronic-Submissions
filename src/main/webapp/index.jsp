

<!DOCTYPE html>

<html lang="en">

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

    <img src='assets/images/icons/search.png'/>

</div><input type="text" placeholder="Search" class="form-control" id='search-text-input'>

                        </form>



                    </li>



                </ul>

                


                <ul class="nav navbar-nav navbar-right navbar-user">

                  



                     <li class="dropdown messages-dropdown">

                        <!-- <button type="submit" class="btn btn-md btn-primary" style="margin:20px;width:120px;">Logout</button> --> 

                        

                    </li>

                    

                       

                        

                    

                </ul>

            </div>

        </nav>



       <div id="page-wrapper" class="inner-wrapper">



            <div class="row">
               <div class="col-lg-12"><h3 class="panel-title"><b>Plagarism Checker</b></h3></div>

                <div class="col-lg-12">
               
                    <div class="panel panel-primary shadows">

                       

                        <div class="panel-body">

                            

                          <form role="form" action="PlagarismServlet" method="post" enctype="multipart/form-data">
                           <ul  class="form-row">
                              
                              <li>
                                 <div class="form-group col-lg-8">

                                    <label>Please Enter your text below and press check plagiarism</label>
        
                                    <textarea class="form-control" rows="4" name="plagarismData"></textarea>
        
                                </div>
                              </li>
                              <li>
                                 <div class="form-group col-lg-8">

                                    <div class="row">
          
                                      <div class="col-lg-6">
          
                                         <label>Or Upload file</label>
          
                                      <input type="file" class="form-control" name="file" placeholder="">
          
                                      </div>
          
                                      <div class="col-lg-6">
          
                                         <label>Exclude URLs(if any)</label>
          
                                      <input class="form-control" placeholder="" name="excludeUrl">
          
                                      </div>
          
                                      
          
                                    </div>
          
                                     
          
                                  </div>
                              </li>
						   </ul>
                              

                              

                      

                        


             
                                
                             
                                 
                                
                                  <button type="submit" class="btn btn-md btn-primary" style="margin:20px;width:200px;">Check Plagiarism</button>

                                   <button type="submit" class="btn btn-md btn-primary" style="margin:20px;width:120px;">Reset</button>
                             

	





                         

                         

                            </form>
                             </div>

                         

                    </div>

                </div>

            </div> <!-- col-lg-12 -->

          

                

            </div>

<!-- tab -->

            

        </div>

    </div>    



</body>

</html>

