#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Script para unir las notas de dos ficheros CSV (UF1 y UF2) en uno solo.

import argparse
import csv
from pathlib import Path

DELIMITER = ";"
ENCODING = "latin-1"

DEFAULT_UF1 = "Notas_Alumnos_UF1.csv"
DEFAULT_UF2 = "Notas_Alumnos_UF2.csv"
DEFAULT_OUTPUT = "notas_alumnos.csv"


def leer_csv(ruta: Path, campo_nota: str):
    """Lee un CSV y devuelve un diccionario con los datos, usando el Id como clave."""
    datos = {}

    with ruta.open("r", encoding=ENCODING, newline="") as f:
        reader = csv.DictReader(f, delimiter=DELIMITER)
        campos = {"Id", "Apellidos", "Nombre", campo_nota}

        if not campos.issubset(reader.fieldnames or []):
            raise ValueError(f"El archivo {ruta} no tiene las cabeceras correctas: {campos}")

        for fila in reader:
            ident = (fila.get("Id") or "").strip()
            if not ident:
                continue

            datos[ident] = {
                "Id": ident,
                "Nombre": (fila.get("Nombre") or "").strip(),
                "Apellidos": (fila.get("Apellidos") or "").strip(),
                campo_nota: (fila.get(campo_nota) or "").strip(),
            }

    return datos


def fusionar_registros(uf1, uf2):
    """Combina los registros de UF1 y UF2 según el Id."""
    ids = set(uf1) | set(uf2)
    fusionados = []

    for ident in ids:
        fila = {"Id": ident, "Nombre": "", "Apellidos": "", "UF1": "", "UF2": ""}

        if ident in uf1:
            fila.update({k: v for k, v in uf1[ident].items() if v})

        if ident in uf2:
            for k, v in uf2[ident].items():
                if v and not fila.get(k):
                    fila[k] = v

        fusionados.append(fila)

    def clave(x):
        try:
            return int(x["Id"])
        except ValueError:
            return x["Id"]

    return sorted(fusionados, key=clave)


def escribir_csv(ruta_salida: Path, filas):
    """Escribe las filas fusionadas en el fichero de salida."""
    campos = ["Id", "Nombre", "Apellidos", "UF1", "UF2"]

    with ruta_salida.open("w", encoding=ENCODING, newline="") as f:
        writer = csv.DictWriter(f, fieldnames=campos, delimiter=DELIMITER)
        writer.writeheader()
        writer.writerows(filas)


def parse_args():
    parser = argparse.ArgumentParser(description="Fusiona las notas de UF1 y UF2.")
    parser.add_argument("--uf1", default=DEFAULT_UF1, help="Ruta del CSV de UF1")
    parser.add_argument("--uf2", default=DEFAULT_UF2, help="Ruta del CSV de UF2")
    parser.add_argument("--out", default=DEFAULT_OUTPUT, help="Ruta del CSV final")
    return parser.parse_args()


def main():
    args = parse_args()

    uf1_path = Path(args.uf1)
    uf2_path = Path(args.uf2)
    salida_path = Path(args.out)

    if not uf1_path.exists():
        raise FileNotFoundError(f"No se encuentra el archivo {uf1_path}")
    if not uf2_path.exists():
        raise FileNotFoundError(f"No se encuentra el archivo {uf2_path}")

    uf1 = leer_csv(uf1_path, "UF1")
    uf2 = leer_csv(uf2_path, "UF2")

    fusionados = fusionar_registros(uf1, uf2)
    escribir_csv(salida_path, fusionados)

    print(f"Generado {salida_path} con {len(fusionados)} alumnos.")


if __name__ == "__main__":
    main()
