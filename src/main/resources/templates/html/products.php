<nav id="head" class="nav-extended">
    <div class="nav-content">
        <ul class="tabs tabs-transparent">
            <li class="tab"><a href="index.php?page=products1" >Nos Cartes Graphiques</a></li>
            <li class="tab"><a href="index.php?page=products2" >Nos Processeurs</a></li>
            <li class="tab"><a href="index.php?page=products3" >Nos Mémoires RAM</a></li>
        </ul>
    </div>
</nav>
    <div class="container">
        <?php
        if(isset($_GET['page'])){
            $page = $_GET['page'];
            $page = strip_tags($page);
            $page = stripslashes($page);
            $page = trim($page);

            switch($page)
            {
                case "products1":
                    include('products/products1.php');
                    break;
                case "products2":
                    include('products/products2.php');
                    break;
                case "products3":
                    include('products/products3.php');
                    break;
                default:
                    include('products/products1.php');
                    break;
            }
        }
        ?>

    </div>
