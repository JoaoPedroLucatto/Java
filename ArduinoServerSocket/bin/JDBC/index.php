<?php
    $message_return = '';


	if (isset($_GET['token'])) {
		include_once '../model/postgresql.php';

		$cnpj = $_GET['token'];

		$select = "SELECT id, buscardados, tempambmin, tempambmax, umidambmin, umidambmax, correntmin, correntmax, editado FROM empreendimentos WHERE cnpj = '$cnpj' AND id_statusregistro = 1";
		$query = pg_query($conn, $select);

		if (pg_num_rows($query) > 0) {
			$row = pg_fetch_assoc($query);

			$temperatura = $_GET['temperatura'];
			$umidade = $_GET['umidade'];

			$insert = "INSERT INTO log_empreendimentos (id_empreendimento, datahorainclusao, temperatura, umidade) VALUES (".$row['id'].", CURRENT_TIMESTAMP(0), $temperatura, $umidade)";
			//$query = pg_query($conn, $insert);


			if ($row['editado'] == 'f') {
				$message_return = '{ "status": "success", "mensagem": "edit", "intervalo": "'.$row['buscardados'].'", "tempmin": "'.$row['tempambmin'].'", "tempmax": "'.$row['tempambmax'].'", "umidmin": "'.$row['umidambmin'].'", "umidmax": "'.$row['umidambmax'].'", "correntmin": "'.$row['correntmin'].'", "correntmax": "'.$row['correntmax'].'" }';

				$update = "UPDATE empreendimentos SET editado = 'f' WHERE id = ".$row['id'];
				// $query = pg_query($conn, $update);
			}
		}

		else {
			$message_return = '{ "status": "error", "mensagem": "token" }';
		}
	}

	else {
		$message_return = '{ "status": "error", "mensagem": "url" }';
	}


	echo $message_return;
?>